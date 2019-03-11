package com.onimurasame.sampler.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.onimurasame.sampler.common.SampleFactory
import com.onimurasame.sampler.common.SampleInfos
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class GdxSamplerLauncher : JFrame() {

    private val windowWidth = 1080
    private val windowHeight = 720
    private val windowSize = Dimension(windowWidth, windowHeight)
    private val cellWidth = 200
    private val canvasWidth = windowWidth - cellWidth
    private var lwjglAWTCanvas: LwjglAWTCanvas? = null

    private lateinit var sampleList: JList<String>

    init {
        title = GdxSamplerLauncher::class.java.simpleName
        minimumSize = windowSize
        size = windowSize
        isResizable = true
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        createControlPanel()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                lwjglAWTCanvas?.stop()
            }
        })

        //launchSample("com.onimurasame.sampler.sample.InputPollingSample")
        // tell window/jframe to resize componente
        pack()
        isVisible = true

    }

    private fun createControlPanel() {
        val controlPanel = JPanel(GridBagLayout())
        val c = GridBagConstraints()

        // scroll pane
        c.apply {
            gridx = 0
            gridy = 0
            fill = GridBagConstraints.VERTICAL
            weighty = 1.0
        }

        sampleList = JList(SampleInfos.getSampleNames())
        sampleList.fixedCellWidth = cellWidth
        sampleList.selectionMode = ListSelectionModel.SINGLE_SELECTION

        // add double click to launch sample
        sampleList.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                if (e?.clickCount == 2) {
                    launchSelectedSample()
                }
            }
        })

        val scrollPane = JScrollPane(sampleList)

        controlPanel.add(scrollPane, c)

        // scroll pane
        c.apply {
            gridx = 0
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weighty = 0.0
        }

        val launchButton = JButton("Launch Sample")
        launchButton.addActionListener { launchSelectedSample() }

        controlPanel.add(launchButton, c)

        //add to main window
        contentPane.add(controlPanel, BorderLayout.WEST)

    }

    private fun launchSelectedSample() {
        val sampleName: String? = sampleList.selectedValue

        if (sampleName.isNullOrBlank()) {
            println("Sample name is null or blank, can't launch")
            return
        } else {
            launchSample(sampleName)
        }
    }

    private fun launchSample(name: String?) {
        println("Launching = $name")
        lwjglAWTCanvas?.stop()

        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

        if (!name.isNullOrBlank()) {
            val sample = SampleFactory.newSample(name!!)

            lwjglAWTCanvas = LwjglAWTCanvas(sample)
            lwjglAWTCanvas?.canvas?.size = Dimension(canvasWidth, windowHeight)
            contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
        }

        pack()

    }

}


fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GdxSamplerLauncher() }
}
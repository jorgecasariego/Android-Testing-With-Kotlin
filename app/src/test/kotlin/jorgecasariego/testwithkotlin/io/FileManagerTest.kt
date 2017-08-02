package jorgecasariego.testwithkotlin.io

import jorgecasariego.AndroidTest
import org.amshove.kluent.shouldEqualTo
import org.junit.After
import org.junit.Test
import java.io.File

/**
 * Created by jorgecasariego on 2/8/17.
 */
class FileManagerTest: AndroidTest() {
    private var fileManager = FileManager()

    @After
    fun tearDown() {
        fileManager.clearDirectory(cacheDir())
    }

    @Test
    fun shouldWriteToFile() {
        val fileToWrite = createDummyFile()
        val fileContent = "content"

        fileManager.writeToFile(fileToWrite, fileContent)

        fileToWrite.exists() shouldEqualTo true
    }

    @Test
    fun shouldHaveCorrectFileContent() {
        val fileToWrite = createDummyFile()
        val fileContent = "content\n"

        fileManager.writeToFile(fileToWrite, fileContent)
        val expectedContent = fileManager.readFileContent(fileToWrite)

        expectedContent shouldEqualTo fileContent
    }

    private fun createDummyFile(): File {
        val dummyFilePath = cacheDir().path + File.separator + "dummyFile"
        return File(dummyFilePath)
    }
}
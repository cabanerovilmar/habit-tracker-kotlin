package com.entalpiya.app.core.utils

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

fun readJsonContent(context: Context): String {
    // Read the JSON file from the assets folder
    val inputStream = context.assets.open("data.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))

    // Read all lines from the file
    val stringBuilder = StringBuilder()
    bufferedReader.use { reader ->
        reader.forEachLine { line ->
            stringBuilder.append(line)
        }
    }

    // Convert the read content to a JSONObject
    val json = JSONObject(stringBuilder.toString())

    // Extract the value of "content" and store in jsonContent variable
    val jsonContent = json.getString("content")

    // Return the properly escaped content for WebView
    return escapeForWebView(jsonContent)
}

// Function to properly escape JSON strings for WebView JavaScript
fun escapeForWebView(content: String): String {
    return content.replace("\\", "\\\\") // Escaping backslashes for WebView compatibility
}

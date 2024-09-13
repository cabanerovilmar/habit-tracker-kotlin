package com.entalpiya.app.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import com.entalpiya.app.R
import kotlin.properties.Delegates

class MarkdownKatexView : WebView {

    private val path: String = "file:///android_asset"

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("SetJavaScriptEnabled")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        settings.apply {
            loadWithOverviewMode = true
            javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = true  // Enabling DOM storage for more complex HTML interactions
        }

        // Setting layer type based on Android version
        setLayerType(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) View.LAYER_TYPE_HARDWARE else View.LAYER_TYPE_SOFTWARE,
            null
        )

        // Applying custom attributes if available
        attrs?.let {
            val math = context.obtainStyledAttributes(attrs, R.styleable.MathView)
            text = math.getString(R.styleable.MathView_text)
            textZoom = math.getInt(R.styleable.MathView_textZoom, 100)
            math.recycle()
        }
    }

    var text: String? by Delegates.observable<String?>("") { _, old, new ->
        if (old != new) update()
    }

//    var textAlign: TextAlign by Delegates.observable(TextAlign.START) { _, old, new ->
//        if (old != new) update()
//    }

    var textColor: String by Delegates.observable("Black") { _, old, new ->
        if (old != new) update()
    }

    var backgroundColor: String by Delegates.observable("White") { _, old, new ->
        if (old != new) update()
    }

    var textZoom: Int = 100
        set(value) {
            settings.textZoom = value
            field = value
        }

    private fun update() {
        // Base URL for loading CSS and JS
        val headScripts = """
            <link rel="stylesheet" href="$path/katex/katex.min.css"/>
            <script defer src="$path/katex/katex.min.js"></script>
            <script defer src="$path/katex/auto-render.min.js"></script>
            
        """.trimIndent()

        // Head styles
        val headStyles = """
            <style>
                body { 
                    margin: 0; padding: 0;
                    color: $textColor;
                    background: $backgroundColor;
                    font-family: ui-sans-serif, -apple-system, system-ui, Segoe UI, Helvetica, Apple Color Emoji, Arial, sans-serif, Segoe UI Emoji, Segoe UI Symbol;
                }
            </style>
        """.trimIndent()

        // Body setup with text and rendering script
        val bodyContent = """
            <script>
                var s = '${text.orEmpty()}';
                document.write(s);
            </script>
        """.trimIndent()

        // Escape JavaScript and HTML special characters
        val escapedText = text.orEmpty()
            .replace("\\", "\\\\") // Escape backslashes
            .replace("'", "\\'")   // Escape single quotes
            .replace("\n", "\\n")  // Convert newlines for JavaScript

        // Function to render math in the document body
        val renderMathScript = """
        <script src="$path/marked.min.js"></script>
        <script>
            const content = '$escapedText';

            function ready() {
                try {
                    const output = document.body;
                    
                    // Render markdown and then LaTeX math expressions
                    output.innerHTML = marked.parse(content);
                    
                    renderMathInElement(output, {
                        delimiters: [
                            { left: "$$", right: "$$", display: true },
                            { left: "$", right: "$", display: false },
                            { left: "\\[", right: "\\]", display: true },
                            { left: "\\(", right: "\\)", display: false },
                        ],
                        throwOnError: false, // Prevent errors from stopping the rendering
                    });
                } catch (error) {
                    console.error('Error rendering content:', error);
                }
            }
        </script>
    """.trimIndent()

        // Complete HTML structure
        val htmlContent = """
            <!DOCTYPE html>
            <html>
                <head>
                    $headScripts
                    $headStyles
                </head>
                <body onload="ready()">
                    $bodyContent
                    $renderMathScript
                </body>
            </html>
        """.trimIndent()

        // Load the generated HTML content into the view
        loadDataWithBaseURL(path, htmlContent, "text/html", "UTF-8", null)
    }
}

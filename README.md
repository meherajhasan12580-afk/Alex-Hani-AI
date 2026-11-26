# Alex Hani AI — starter Android app

This is an initial Android prototype. It includes a simple Bengali speech/text interface and offline sample responses. It does not yet include a Gemini/ChatGPT connection, always-on wake word, background listening, contact access, WhatsApp/SMS automation, or full phone accessibility control. Those require additional development, permissions, testing, and in some cases API credentials.

## Build APK with GitHub Actions

1. Upload the **contents** of this folder to the root of your GitHub repository (do not upload this ZIP as a single file).
2. Open the **Actions** tab.
3. Select **Build Alex Hani AI APK** and run it, or push a commit to `main`.
4. When the workflow succeeds, open its run and download the `Alex-Hani-AI-debug-apk` artifact.

The workflow installs Java 17 and Gradle 8.7 directly. It deliberately does not use Gradle wrapper caching, because this starter source does not contain Gradle wrapper files.

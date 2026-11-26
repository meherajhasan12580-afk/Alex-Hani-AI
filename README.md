# Alex Hani AI — starter Android app

This is an initial Android prototype. It supports tap-to-speak Bengali speech recognition (using the phone's speech service) and text-to-speech replies with a few simple offline responses.

It does **not** yet include a Gemini/ChatGPT connection, always-on wake word, background listening, contact access, WhatsApp/SMS automation, or full phone accessibility control. Those require additional development, permissions, testing, and in some cases API credentials.

## Build
GitHub Actions runs the Android build when code is pushed to `main`, or when the workflow is manually started from the Actions tab. The APK is published as an Actions artifact named `Alex-Hani-AI-debug-apk`.

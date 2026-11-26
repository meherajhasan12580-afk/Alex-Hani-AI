package com.alexhani.ai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final int SPEECH_REQUEST = 100;
    private TextView status;
    private TextToSpeech tts;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(28, 28, 28, 28);
        layout.setBackgroundColor(0xFF171421);

        TextView title = new TextView(this);
        title.setText("Alex Hani AI"); title.setTextSize(30); title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);
        status = new TextView(this);
        status.setText("হ্যালো! কথা বলতে নিচের বাটনে চাপ দাও।"); status.setTextSize(18);
        status.setTextColor(0xFFE6E0F2); status.setGravity(Gravity.CENTER);
        status.setPadding(0, 32, 0, 32);
        Button talk = new Button(this); talk.setText("কথা বলুন / Speak");
        talk.setOnClickListener(v -> startListening());
        layout.addView(title); layout.addView(status); layout.addView(talk);
        setContentView(layout);

        tts = new TextToSpeech(this, result -> {
            if (result == TextToSpeech.SUCCESS) {
                int language = tts.setLanguage(new Locale("bn", "BD"));
                if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED)
                    tts.setLanguage(Locale.US);
            }
        });
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "bn-BD");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "আপনার কথা বলুন");
        try { startActivityForResult(intent, SPEECH_REQUEST); }
        catch (Exception e) { Toast.makeText(this, "Speech recognition is unavailable", Toast.LENGTH_LONG).show(); }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST && resultCode == RESULT_OK && data != null) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && !matches.isEmpty()) {
                String heard = matches.get(0);
                String reply = makeReply(heard);
                status.setText("আপনি: " + heard + "\n\nAlex: " + reply);
                if (tts != null) tts.speak(reply, TextToSpeech.QUEUE_FLUSH, null, "alex-reply");
            }
        }
    }

    private String makeReply(String text) {
        String s = text.toLowerCase(Locale.ROOT);
        if (s.contains("হ্যালো") || s.contains("hello") || s.contains("হাই")) return "হ্যালো! আমি Alex Hani AI। কেমন আছেন?";
        if (s.contains("সময়") || s.contains("time")) return "এখন সময় " + new java.text.SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new java.util.Date());
        if (s.contains("তোমার নাম") || s.contains("your name")) return "আমার নাম Alex Hani AI।";
        return "আপনার কথা শুনেছি। এই প্রাথমিক সংস্করণে এখনো অনলাইন AI যুক্ত করা হয়নি।";
    }

    @Override protected void onDestroy() {
        if (tts != null) { tts.stop(); tts.shutdown(); }
        super.onDestroy();
    }
}

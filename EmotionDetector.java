import java.util.*;

public class EmotionDetector {

    // Emotion keywords stored in HashMap
    private static Map<String, List<String>> emotionMap = new HashMap<>();

    static {
        emotionMap.put("Happy", Arrays.asList("happy", "joy", "excited", "great", "awesome", "good"));
        emotionMap.put("Sad", Arrays.asList("sad", "unhappy", "depressed", "down", "cry"));
        emotionMap.put("Angry", Arrays.asList("angry", "mad", "furious", "annoyed", "irritated"));
    }

    // Method to detect emotion
    public static String detectEmotion(String text) {
        text = text.toLowerCase();

        Map<String, Integer> scores = new HashMap<>();

        // Initialize scores
        for (String emotion : emotionMap.keySet()) {
            scores.put(emotion, 0);
        }

        // Count matches
        for (String emotion : emotionMap.keySet()) {
            for (String word : emotionMap.get(emotion)) {
                if (text.contains(word)) {
                    scores.put(emotion, scores.get(emotion) + 1);
                }
            }
        }

        // Find highest score
        String detectedEmotion = "Neutral";
        int maxScore = 0;

        for (String emotion : scores.keySet()) {
            if (scores.get(emotion) > maxScore) {
                maxScore = scores.get(emotion);
                detectedEmotion = emotion;
            }
        }

        return detectedEmotion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Emotion Detector ===");
        System.out.print("Enter your sentence: ");

        String input = scanner.nextLine();

        String emotion = detectEmotion(input);

        // Emoji output
        String emoji;
        switch (emotion) {
            case "Happy":
                emoji = "😊";
                break;
            case "Sad":
                emoji = "😢";
                break;
            case "Angry":
                emoji = "😡";
                break;
            default:
                emoji = "😐";
        }

        System.out.println("Detected Emotion: " + emotion + " " + emoji);

        scanner.close();
    }
}
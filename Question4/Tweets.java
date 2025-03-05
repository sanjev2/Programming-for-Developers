package Question4;

import java.text.*;
import java.util.*;

public class Tweets {
    /**
     * Entry point of the program.
     * This program processes tweets, keeps only those from February 2024,
     * counts hashtags, sorts them, and then displays the three most popular ones.
     */
    public static void main(String[] args) {
        // Sample dataset containing user ID, tweet ID, tweet text, and tweet date.
        List<Map<String, String>> tweets = new ArrayList<>();

        // Populating the tweet list using a helper function
        tweets.add(createTweet("201", "21", "Loving the vibes today! #GoodVibes #ChillMode", "2024-02-02"));
        tweets.add(createTweet("202", "22", "Work hustle never stops! #Grind #Hustle", "2024-02-03"));
        tweets.add(createTweet("203", "23", "Exploring new AI trends! #AI #TechWorld", "2024-02-04"));
        tweets.add(createTweet("204", "24", "Sunny days ahead! #GoodVibes #Sunshine", "2025-02-05"));
        tweets.add(createTweet("205", "25", "AI revolution is here! #AI #FutureTech", "2024-02-06"));
        tweets.add(createTweet("205", "25", "AI revolution is here! #AI #FutureTech", "2025-03-07"));
        tweets.add(createTweet("206", "26", "Success comes with persistence! #Hustle #Motivation", "2024-02-07"));
        tweets.add(createTweet("207", "27", "Nature therapy always works. #Peaceful #NatureLover", "2024-02-08"));

        // Establishing the expected format for dates (YYYY-MM-DD)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        try {
            // Converting the boundary dates for February 2024 into Date objects
            startDate = dateFormat.parse("2024-02-01");
            endDate = dateFormat.parse("2024-02-29");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // A storage for counting hashtags (key: hashtag, value: occurrences)
        Map<String, Integer> hashtagCount = new HashMap<>();

        // Iterating through the list of tweets
        for (Map<String, String> tweet : tweets) {
            // Extract the date of the tweet
            String tweetDateStr = tweet.get("tweet_date");

            try {
                Date tweetDate = dateFormat.parse(tweetDateStr);

                // Proceed only if the tweet falls within February 2024
                if (!tweetDate.before(startDate) && !tweetDate.after(endDate)) {
                    // Extract the tweet message
                    String tweetText = tweet.get("tweet");

                    // Split the tweet into words
                    String[] words = tweetText.split(" ");

                    // Process each word to check for hashtags
                    for (String word : words) {
                        if (word.startsWith("#")) {
                            // Convert to lowercase to ensure uniform counting
                            String hashtag = word.toLowerCase();

                            // Update the count in the map
                            hashtagCount.put(hashtag, hashtagCount.getOrDefault(hashtag, 0) + 1);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Convert the map into a list of entries for sorting
        List<Map.Entry<String, Integer>> sortedHashtags = new ArrayList<>(hashtagCount.entrySet());

        // Sorting: first by number of appearances (descending), then alphabetically if tied
        sortedHashtags.sort((a, b) -> {
            int countComparison = b.getValue().compareTo(a.getValue()); // Sort by count (high to low)
            if (countComparison != 0) return countComparison;

            return a.getKey().compareTo(b.getKey()); // Sort alphabetically if counts are the same
        });

        // Formatting the table output
        System.out.println("+-------------+---------+");
        System.out.println("|   HASHTAG   |  COUNT  |");
        System.out.println("+-------------+---------+");

        // Display up to three most used hashtags
        for (int i = 0; i < Math.min(3, sortedHashtags.size()); i++) {
            Map.Entry<String, Integer> entry = sortedHashtags.get(i);

            // Print each hashtag and its count
            System.out.printf("| %-11s | %-7d |%n", entry.getKey(), entry.getValue());
        }

        // Table footer
        System.out.println("+-------------+---------+");
    }

    /**
     * Utility method to construct a tweet record.
     * @param userId ID of the tweet's author
     * @param tweetId Unique identifier of the tweet
     * @param tweet Message content
     * @param tweetDate Posting date
     * @return A map containing all tweet details
     */
    private static Map<String, String> createTweet(String userId, String tweetId, String tweet, String tweetDate) {
        // Creating a map structure to store tweet data
        Map<String, String> tweetData = new HashMap<>();

        // Adding tweet information into the map
        tweetData.put("user_id", userId);
        tweetData.put("tweet_id", tweetId);
        tweetData.put("tweet", tweet);
        tweetData.put("tweet_date", tweetDate);

        // Returning the completed tweet record
        return tweetData;
    }
}

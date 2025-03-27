public class BajajHealthChallenge {
    public static void main(String[] args) {
        try {
            
            var file = new java.io.File("input.json"); // Open the JSON file
            var reader = new java.util.Scanner(file); 
            var jsonContent = new StringBuilder(); // StringBuilder to store file content
            
            while (reader.hasNextLine()) {
                jsonContent.append(reader.nextLine());
            }
            reader.close();

            // Extract first_name and roll_number from JSON  
            var jsonString = jsonContent.toString();
            var firstName = extractValue(jsonString, "first_name").toLowerCase();
            var rollNumber = extractValue(jsonString, "roll_number").toLowerCase();

            // Generate MD5 hash
            var md5Hash = generateMD5Hash(firstName + rollNumber);

            // Write output to output.txt file
            var writer = new java.io.FileWriter("output.txt");
            writer.write(md5Hash);
            writer.close();
            // Print success message
            System.out.println("MD5 hash stored in output.txt: " + md5Hash);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String extractValue(String json, String key) {       // Method to extract value from JSON manually
        var keyIndex = json.indexOf("\"" + key + "\"");              // Find key in JSON
        if (keyIndex == -1) return "";                               // If key not found, return empty
        
        var colonIndex = json.indexOf(":", keyIndex);
        var startQuote = json.indexOf("\"", colonIndex + 1);
        var endQuote = json.indexOf("\"", startQuote + 1);
        
        return json.substring(startQuote + 1, endQuote).trim();
    }

    private static String generateMD5Hash(String input) throws java.security.NoSuchAlgorithmException {
        var md = java.security.MessageDigest.getInstance("MD5");
        var hashBytes = md.digest(input.getBytes());            // Convert string to hash
        var hexString = new StringBuilder();

        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));        // Convert each byte to hexadecimal
        }
        return hexString.toString();
    }
}


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FolderSweeper {
    
    // Define categories with extensions
    private static final Map<String, List<String>> CATEGORIES = new HashMap<>();
    
    static {
        CATEGORIES.put("Images", Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg", ".webp", ".ico"));
        CATEGORIES.put("Documents", Arrays.asList(".pdf", ".docx", ".doc", ".txt", ".xlsx", ".xls", ".pptx", ".ppt", ".csv", ".rtf", ".odt"));
        CATEGORIES.put("Videos", Arrays.asList(".mp4", ".mov", ".avi", ".mkv", ".flv", ".wmv", ".webm", ".m4v"));
        CATEGORIES.put("Music", Arrays.asList(".mp3", ".wav", ".aac", ".flac", ".m4a", ".ogg", ".wma"));
        CATEGORIES.put("Archives", Arrays.asList(".zip", ".rar", ".7z", ".tar", ".gz", ".bz2", ".xz"));
        CATEGORIES.put("Code", Arrays.asList(".py", ".js", ".html", ".css", ".java", ".cpp", ".c", ".go", ".rs", ".swift", ".php", ".json", ".xml", ".yaml", ".yml"));
        CATEGORIES.put("Executables", Arrays.asList(".exe", ".msi", ".dmg", ".pkg", ".deb", ".rpm", ".appimage"));
    }
    
    public static void main(String[] args) {
        // **CHANGE THIS PATH** - Your Downloads folder
        String downloadsPath = "C:\\Users\\YourName\\Downloads"; // <-- CHANGE THIS!
        
        File folder = new File(downloadsPath);
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("❌ Invalid folder path: " + downloadsPath);
            return;
        }
        
        // Get all files (excluding directories)
        File[] files = folder.listFiles(File::isFile);
        
        if (files == null || files.length == 0) {
            System.out.println("📁 No files found in the folder.");
            return;
        }
        
        // PREVIEW MODE - Show what will happen
        System.out.println("🔍 PREVIEW: These files will be organized:");
        System.out.println("--------------------------------------------------");
        
        Map<String, List<String>> fileMap = new HashMap<>();
        
        for (File file : files) {
            String fileName = file.getName();
            String extension = getFileExtension(fileName);
            String category = getCategory(extension);
            
            fileMap.computeIfAbsent(category, k -> new ArrayList<>()).add(fileName);
            // FIXED: Using "->" instead of "→"
            System.out.println("📄 " + fileName + " -> " + category + "/");
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("Total files to organize: " + files.length);
        
        // Ask for confirmation - accepts "y" or "yes"
        System.out.print("\n❓ Do you want to proceed? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("❌ Cancelled. No files were moved.");
            scanner.close();
            return;
        }
        
        // ORGANIZE FILES
        System.out.println("\n🚀 Organizing files...");
        int movedCount = 0;
        
        for (File file : files) {
            String fileName = file.getName();
            String extension = getFileExtension(fileName);
            String category = getCategory(extension);
            
            try {
                // Create category folder if it doesn't exist
                Path categoryPath = Paths.get(downloadsPath, category);
                if (!Files.exists(categoryPath)) {
                    Files.createDirectory(categoryPath);
                }
                
                // Handle duplicate filenames
                Path destPath = Paths.get(downloadsPath, category, fileName);
                if (Files.exists(destPath)) {
                    String name = fileName.substring(0, fileName.lastIndexOf('.'));
                    String ext = fileName.substring(fileName.lastIndexOf('.'));
                    int counter = 1;
                    while (Files.exists(Paths.get(downloadsPath, category, name + "_" + counter + ext))) {
                        counter++;
                    }
                    destPath = Paths.get(downloadsPath, category, name + "_" + counter + ext);
                }
                
                // Move file
                Files.move(file.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
                movedCount++;
                System.out.println("✅ Moved: " + fileName + " -> " + category + "/");
                
            } catch (IOException e) {
                System.err.println("❌ Error moving file: " + fileName + " - " + e.getMessage());
            }
        }
        
        System.out.println("\n🎉 DONE! Organized " + movedCount + " files.");
        scanner.close();
    }
    
    // Get file extension
    private static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1) {
            return ""; // No extension
        }
        return fileName.substring(lastDot).toLowerCase();
    }
    
    // Find category for extension
    private static String getCategory(String extension) {
        for (Map.Entry<String, List<String>> entry : CATEGORIES.entrySet()) {
            if (entry.getValue().contains(extension)) {
                return entry.getKey();
            }
        }
        return "Others"; // Default category
    }
}
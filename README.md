# 📂 FolderSweeper

FolderSweeper is a Java-based utility that automatically organizes files in your Downloads folder into categorized subfolders.

Instead of manually sorting files, FolderSweeper scans your Downloads folder, previews the changes, asks for confirmation, and moves files into folders like Images, Documents, Videos, Music, Code, Archives, and more.

---

## ✨ Features

- 📁 Automatically organizes files by extension
- 🔍 Preview mode before moving files
- ✅ User confirmation before execution
- 📄 Supports multiple file categories
- 🖼️ Images
- 📑 Documents
- 🎥 Videos
- 🎵 Music
- 📦 Archives
- 💻 Code Files
- ⚙️ Executables
- 📂 Others
- 🔄 Handles duplicate filenames automatically
- ❌ Graceful error handling
- 🚀 Lightweight and easy to use

---

## 🛠️ Technologies Used

- Java
- Java File I/O
- Java NIO (`java.nio.file`)
- Collections Framework
- Exception Handling

---

## 📂 Project Structure

```
FolderSweeper.java
```

---

## 🚀 How It Works

1. Select your Downloads folder path.
2. The application scans all files.
3. Displays a preview of where each file will be moved.
4. Asks for confirmation.
5. Creates category folders if needed.
6. Moves files into their respective folders.
7. Renames duplicate files automatically.
8. Displays a completion summary.

---

## 📸 Example Output

```
🔍 PREVIEW

Asta-1.jpg
→ Images/

Resume.pdf
→ Documents/

Project.zip
→ Archives/

Do you want to proceed? (yes/no)

🚀 Organizing...

✅ Moved Asta-1.jpg
✅ Moved Resume.pdf

🎉 DONE!
```

---

## 📚 Java Concepts Demonstrated

- File Handling
- Java NIO
- Collections (HashMap & ArrayList)
- Exception Handling
- User Input (Scanner)
- Loops & Conditional Logic
- Static Initialization
- Path Manipulation

---

## 🔮 Future Improvements

- GUI using JavaFX
- Drag & Drop Folder Selection
- Undo Last Organization
- Custom Categories
- Recursive Folder Support
- Configuration File
- Logging
- Scheduled Automatic Cleanup

---

## 👨‍💻 Author

**Anand Kumar**

If you found this project helpful, consider giving it a ⭐ on GitHub.

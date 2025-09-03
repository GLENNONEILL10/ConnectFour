# ConnectFour
ConnectFour

A classic Connect 4 game written in Java, with both Console and GUI gameplay modes.

Drop discs into a 7×6 grid and be the first to connect four horizontally, vertically, or diagonally.

✨ Features

Two play modes: Console and Graphical UI

Two-player local play

Valid move checking, win/draw detection

Clear board rendering and turn prompts (console) / polished board (GUI)

Simple, readable Java code structure for learning or extending

🧠 Rules (Quick Recap)

Players alternate dropping pieces into one of 7 columns. Pieces fall to the lowest available cell.
The first player to make four in a row (any direction) wins. If the board fills without a winner, it’s a draw.

📦 Tech Stack

Language: Java (JDK 17+ recommended)

UI: Java Swing / JavaFX (depending on your implementation)

Build: Works with plain javac, Gradle, or Maven (see options below)

🚀 Getting Started

Pick the option that matches your repo setup. If you’re not using Gradle/Maven, the “Plain Java” path will work.

1) Clone
git clone https://github.com/GLENNONEILL10/ConnectFour.git
cd ConnectFour

2) Run it
Option A — Gradle (if the repo includes build.gradle / gradlew)
./gradlew run              # macOS/Linux
# or
gradlew.bat run            # Windows

Option B — Maven (if the repo includes pom.xml)
mvn clean package
java -jar target/*-with-dependencies.jar   # or the jar produced by your build

Option C — Plain Java (no build tool)

Assuming sources are under src/ and your main class is Main (adjust as needed):

# Compile
javac -d out $(find src -name "*.java")
# Run
java -cp out Main

🎮 Usage

Depending on how your Main class handles modes, you’ll typically run either:

# Console mode (example)
java -cp out Main --mode console
# GUI mode (example)
java -cp out Main --mode gui


If your project uses separate entry points (e.g., ConsoleMain and GuiMain), run those directly:

java -cp out game.console.ConsoleMain
java -cp out game.gui.GuiMain


Not sure which applies? Tell me your main class / build tool and I’ll lock these commands to your exact setup.

🗂️ Project Structure (typical)
ConnectFour/
├─ src/
│  ├─ game/
│  │  ├─ model/        # Board, Cell, Player enums
│  │  ├─ logic/        # Move validation, win/draw checks
│  │  ├─ console/      # Console renderer + loop
│  │  └─ gui/          # Swing/JavaFX UI (BoardPanel, GameWindow, etc.)
│  └─ Main.java        # Entry point selecting Console or GUI
├─ README.md
└─ (build files)

✅ Tests (optional)

If you have tests (e.g., JUnit), they’ll run via your build tool:

./gradlew test
# or
mvn test

🛣️ Roadmap Ideas

Add AI opponent (Minimax/Alpha-Beta or MCTS)

Difficulty levels

Undo/redo, move history

Sound effects / animations for the GUI

Online multiplayer

🤝 Contributing

Fork the repo

Create a feature branch: git checkout -b feature/your-idea

Commit changes: git commit -m "Add your feature"

Push: git push origin feature/your-idea

Open a PR

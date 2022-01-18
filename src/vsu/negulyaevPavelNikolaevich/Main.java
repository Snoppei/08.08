package vsu.negulyaevPavelNikolaevich;

import util.ArrayUtils;
import util.SwingUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        if(needCmd()) runCmd();
        if(needWindow()) winMain();
    }
    private static boolean needCmd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Запустить интерфейс коммандной строки? Введите true/false: ");
        return scanner.nextBoolean();
    }

    private static boolean needWindow() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Запустить оконное приложение? Введите true/false: ");
        return scanner.nextBoolean();
    }
    private static void runCmd() throws IOException {
        CmdCommands cmdCommand = CmdCommands.STATIC;
        Scanner scanner = new Scanner(System.in);

        String command;
        String inputFile;
        String outputFile = "-";
        int[][] arr = new int[][] {
                {3, 5, 9},
                {2, 8, 8},
                {1, 3, 2},
        };
        int[][] sortedArr;

        System.out.println("Комманды:");
        System.out.println("-run     - запустить программу");
        System.out.println("-help    - показать все комманды");
        System.out.println("-exit    - выйти из программы");
        System.out.println("-read    - считать массив с консоли");
        System.out.println("-input   - указать путь к файлу ввода");
        System.out.println("-output  - указать путь к файлу вывода");

        while (cmdCommand != CmdCommands.EXIT) {
            command = scanner.next();
            if (Objects.equals(command, "-run")) {
                cmdCommand = CmdCommands.RUN;
            } else if (Objects.equals(command, "-help")) {
                cmdCommand = CmdCommands.HELP;
            } else if (Objects.equals(command, "-exit")) {
                cmdCommand = CmdCommands.EXIT;
            } else if (Objects.equals(command, "-read")) {
                cmdCommand = CmdCommands.READ_FROM_CONSOLE;
            } else if (Objects.equals(command, "-input")) {
                cmdCommand = CmdCommands.ENTER_INPUT_FILE;
            } else if (Objects.equals(command, "-output")) {
                cmdCommand = CmdCommands.ENTER_OUTPUT_FILE;
            } else {
                System.out.println("Команда не найдена, попробуйте еще раз: ");
            }

            switch (cmdCommand) {
                case EXIT -> System.exit(1);
                case RUN -> {
                    Logic.writeIntArray2ToConsole(arr);
                    sortedArr = Logic.sort(arr);
                    if (!Objects.equals(outputFile, "-")) {
                        ArrayUtils.writeArrayToFile(outputFile, sortedArr);
                    }
                    System.out.println("Происходит сортировка...");
                    Logic.writeIntArray2ToConsole(sortedArr);
                }
                case READ_FROM_CONSOLE -> {
                    arr = ArrayUtils.readIntArray2FromConsole();
                    Logic.writeIntArray2ToConsole(arr);
                }
                case ENTER_INPUT_FILE -> {
                    System.out.print("Введите путь к файлу ввода: ");
                    inputFile = scanner.next();
                    arr = Logic.readIntArray2FromFile(inputFile);
                    Logic.writeIntArray2ToConsole(arr);

                }
                case ENTER_OUTPUT_FILE -> {
                    System.out.print("Введите путь к файлу вывода: ");
                    outputFile = scanner.next();
                }
                case HELP -> {
                    System.out.println("Комманды:");
                    System.out.println("-run     - запустить программу");
                    System.out.println("-help    - показать все комманды");
                    System.out.println("-exit    - выйти из программы");
                    System.out.println("-read    - считать массив с консоли");
                    System.out.println("-input   - указать путь к файлу ввода");
                    System.out.println("-output  - указать путь к файлу вывода");
                }
            }
        }
    }
    public static void winMain() {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
}

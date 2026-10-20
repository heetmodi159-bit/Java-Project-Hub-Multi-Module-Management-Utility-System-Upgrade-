package OS_System;

import java.util.ArrayList;
import java.util.List;

/** Shared square console layout used by each application module. */
public final class OSConsoleUI {

    private static final int WIDTH = 61;
    private static final int INNER_WIDTH = WIDTH - 2;
    private static final int CARD_WIDTH = 26;
    private static final int CARD_INNER_WIDTH = CARD_WIDTH - 2;

    private OSConsoleUI() {
    }

    public static void printSquareMenu(String title, String subtitle, String[] options) {
        System.out.println();
        printBorder('┌', '─', '┐');
        printRow(center("O S   //   " + title.toUpperCase(), INNER_WIDTH));
        printBorder('├', '─', '┤');
        printRow(center(title.toUpperCase(), INNER_WIDTH));
        printRow(center(subtitle.toUpperCase(), INNER_WIDTH));
        printBorder('├', '─', '┤');

        for (int i = 0; i < options.length; i += 2) {
            String left = options[i] == null ? "" : options[i];
            String right = i + 1 < options.length && options[i + 1] != null
                    ? options[i + 1] : "";
            List<String> leftLines = wrap(left, CARD_INNER_WIDTH);
            List<String> rightLines = wrap(right, CARD_INNER_WIDTH);
            int rows = Math.max(1, Math.max(leftLines.size(), rightLines.size()));
            String cardTop = "┌" + repeat('─', CARD_INNER_WIDTH) + "┐";
            String cardBottom = "└" + repeat('─', CARD_INNER_WIDTH) + "┘";

            printRow("  " + cardTop + "   " + cardTop + "  ");
            for (int j = 0; j < rows; j++) {
                String leftLine = j < leftLines.size() ? leftLines.get(j) : "";
                String rightLine = j < rightLines.size() ? rightLines.get(j) : "";
                String leftCard = "│" + center(leftLine, CARD_INNER_WIDTH) + "│";
                String rightCard = "│" + center(rightLine, CARD_INNER_WIDTH) + "│";
                printRow("  " + leftCard + "   " + rightCard + "  ");
            }
            printRow("  " + cardBottom + "   " + cardBottom + "  ");
            if (i + 2 < options.length) {
                printRow("");
            }
        }

        printBorder('├', '─', '┤');
        printRow(center("OS:// STATUS: READY  |  SELECT AN OPTION", INNER_WIDTH));
        printBorder('└', '─', '┘');
        System.out.println();
    }

    public static void printBanner(String title, String subtitle) {
        System.out.println();
        printBorder('┌', '─', '┐');
        printRow(center("O S   //   " + title.toUpperCase(), INNER_WIDTH));
        printBorder('├', '─', '┤');
        printRow(center(title.toUpperCase(), INNER_WIDTH));
        if (subtitle != null && !subtitle.trim().isEmpty()) {
            for (String line : wrap(subtitle, INNER_WIDTH - 4)) {
                printRow(center(line, INNER_WIDTH));
            }
        }
        printBorder('└', '─', '┘');
    }

    public static void printPanel(String title, String text) {
        System.out.println();
        printBorder('┌', '─', '┐');
        printRow(center(title.toUpperCase(), INNER_WIDTH));
        printBorder('├', '─', '┤');
        for (String line : wrap(text == null ? "" : text, INNER_WIDTH - 4)) {
            printRow("  " + padRight(line, INNER_WIDTH - 4) + "  ");
        }
        printBorder('└', '─', '┘');
    }

    public static void printSection(String title) {
        System.out.println();
        printBorder('┌', '─', '┐');
        printRow(center(title.toUpperCase(), INNER_WIDTH));
        printBorder('└', '─', '┘');
    }

    private static void printBorder(char left, char fill, char right) {
        System.out.println(left + repeat(fill, WIDTH - 2) + right);
    }

    private static void printRow(String value) {
        String row = value == null ? "" : value;
        if (row.length() > INNER_WIDTH) {
            row = row.substring(0, INNER_WIDTH);
        }
        System.out.println("│" + padRight(row, INNER_WIDTH) + "│");
    }

    private static String center(String value, int width) {
        String text = value == null ? "" : value;
        if (text.length() > width) {
            text = text.substring(0, width);
        }
        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;
        return repeat(' ', left) + text + repeat(' ', right);
    }

    private static String padRight(String value, int width) {
        String text = value == null ? "" : value;
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        return text + repeat(' ', width - text.length());
    }

    private static String repeat(char value, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(value);
        }
        return result.toString();
    }

    private static List<String> wrap(String value, int width) {
        List<String> result = new ArrayList<>();
        String text = value == null ? "" : value.trim();
        if (text.isEmpty()) {
            result.add("");
            return result;
        }

        StringBuilder line = new StringBuilder();
        for (String word : text.split("\\s+")) {
            if (word.length() > width) {
                if (line.length() > 0) {
                    result.add(line.toString());
                    line.setLength(0);
                }
                for (int start = 0; start < word.length(); start += width) {
                    result.add(word.substring(start, Math.min(start + width, word.length())));
                }
            } else if (line.length() == 0) {
                line.append(word);
            } else if (line.length() + 1 + word.length() <= width) {
                line.append(' ').append(word);
            } else {
                result.add(line.toString());
                line.setLength(0);
                line.append(word);
            }
        }
        if (line.length() > 0) {
            result.add(line.toString());
        }
        return result;
    }
}

package gui;

/**
 * A class to add "\n" into a string so as to break it up at a blank into lines of a specific
 * length. When doing so, blanks at the end of a line (except the first line) and blanks at the end
 * of a line (except the last one) are removed.
 */
public class SplitString {
    /**
     * Return a String the same as the parameter, except that "\n" is used to divide the string into
     * lines split at a blank, with blanks on either side of the "\n" removed.
     * 
     * @param s the original string
     * @param lineLength the length for the lines to be formed
     * @return the string with end-of-line characters to separate the lines
     */
    public static String at(String s, int lineLength) {
        StringBuilder str = new StringBuilder(s);
        int lineStart = 0;
        int breakPoint = lineLength; // String position for first character of the next line
        while (breakPoint < str.length()) {
            // Find the sequence of blanks that are between the end of this line
            // and start of next line.
            // Find the blank to be used for the line split
            int cursor = breakPoint;
            while (cursor >= lineStart && str.charAt(cursor) != ' ')
                cursor--;
            int firstBlank; // first blank to be replaced
            int lastBlank; // the last blank to be replaced
            if (cursor >= lineStart) {
                breakPoint = cursor;
                // scan back across blanks to the first blank
                while (cursor >= lineStart && str.charAt(cursor) == ' ')
                    cursor--;
                firstBlank = cursor + 1;
                // scan forward across blanks
                cursor = breakPoint;
                while (cursor < str.length() && str.charAt(cursor) == ' ')
                    cursor++;
                lastBlank = cursor - 1;
            } else {
                // no blank in the line to split at so the line overflows,
                // and find the next blank
                cursor = breakPoint;
                while (cursor < str.length() && str.charAt(cursor) != ' ')
                    cursor++;
                firstBlank = cursor;
                while (cursor < str.length() && str.charAt(cursor) == ' ')
                    cursor++;
                lastBlank = cursor - 1;
            }
            if (firstBlank == lineStart) {
                // the only blanks are at the start of the line, so just remove them
                str.replace(firstBlank, lastBlank + 1, "");
                lineStart = firstBlank;
            } else if (lastBlank >= firstBlank) {
                // remove blanks in the sequence and replace by end of line
                str.replace(firstBlank, lastBlank + 1, "\n");
                lineStart = firstBlank + 1;
            }
            breakPoint = lineStart + lineLength;
        }
        return str.toString();
    }

    public static void main(String[] agrs) {
        System.out.println(SplitString.at("1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3", 10));
        System.out.println();
        System.out.println(SplitString.at("1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3", 5));
        System.out.println();
        System.out.println(SplitString.at("1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3", 3));
        System.out.println();
        System.out.println(SplitString.at("1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3", 2));
        System.out.println();
        System.out.println(SplitString.at("1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3", 4));
        System.out.println();
        System.out.println(SplitString.at("1     7  0 2        1 3       1 3", 3));
        System.out.println();
        System.out.println(SplitString.at("12 45678 012     8901 34  7   1 3", 5));
        System.out.println();
        System.out.println(SplitString.at("  3456789    45678901  456789 123", 5));
    }
}

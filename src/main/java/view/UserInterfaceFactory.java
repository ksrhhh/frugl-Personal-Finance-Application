package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UserInterfaceFactory {

    private static final int PADDING = 10;
    private static final int TITLE_FONT_SIZE = 18;
    private static final int BUTTON_FONT_SIZE = 13;
    private static final int BUTTON_PADDING_VERTICAL = 8;
    private static final int BUTTON_PADDING_HORIZONTAL = 16;
    private static final Color HEADER_BORDER_COLOR = new Color(224, 224, 224);
    private static final Color HEADER_BACKGROUND = Color.WHITE;
    private static final Color HEADER_TEXT_COLOR = new Color(33, 33, 33);
    private static final Color LOGO_TEXT_COLOR = new Color(25, 118, 210);
    private static final Color SECONDARY_BUTTON_BACKGROUND_COLOR = new Color(238, 238, 238);
    private static final Color SECONDARY_BUTTON_TEXT_COLOR = new Color(66, 66, 66);
    private static final String SANS_SERIF_FONT_FAMILY = "SansSerif";

    /**
     * Creates a JLabel with the specified text, centered alignment, and font size.
     * Adds padding around the label using an empty border.
     *
     * @param text the text to display on the label
     * @param fontSize the font size to apply to the label text
     * @return a styled JLabel instance
     */

    public static JLabel createTitleLabel(String text, int fontSize) {
        final JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        return label;
    }
    /**
     * Creates a JButton with the specified text and attaches an ActionListener.
     *
     * @param text the text to display on the button
     * @param listener the ActionListener to attach to the button
     * @return a JButton instance with the specified text and listener
     */

    public static JButton createButton(String text, ActionListener listener) {
        final JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    /**
     * Creates a clean header panel to be placed at the top of a view.
     *
     * @param viewTitle the title of the current view
     * @return a styled header {@link JPanel}
     */
    public static JPanel createHeader(String viewTitle) {
        final JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BACKGROUND);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, HEADER_BORDER_COLOR));

        final JLabel logoLabel = new JLabel("$frugl");
        logoLabel.setForeground(LOGO_TEXT_COLOR);
        logoLabel.setFont(new Font(SANS_SERIF_FONT_FAMILY, Font.BOLD, TITLE_FONT_SIZE));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        final JLabel titleLabel = new JLabel(viewTitle, SwingConstants.CENTER);
        titleLabel.setForeground(HEADER_TEXT_COLOR);
        titleLabel.setFont(new Font(SANS_SERIF_FONT_FAMILY, Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        final JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        final JLabel spacerLabel = new JLabel();
        spacerLabel.setPreferredSize(logoLabel.getPreferredSize());
        final JPanel spacerPanel = new JPanel(new BorderLayout());
        spacerPanel.setOpaque(false);
        spacerPanel.add(spacerLabel, BorderLayout.CENTER);

        header.add(logoPanel, BorderLayout.WEST);
        header.add(titleLabel, BorderLayout.CENTER);
        header.add(spacerPanel, BorderLayout.EAST);

        return header;
    }

    /**
     * Applies the primary blue style to the given button.
     *
     * @param button the button to style
     */
    public static void stylePrimaryButton(JButton button) {
        button.setBackground(LOGO_TEXT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font(SANS_SERIF_FONT_FAMILY, Font.BOLD, BUTTON_FONT_SIZE));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(
                BUTTON_PADDING_VERTICAL, BUTTON_PADDING_HORIZONTAL,
                BUTTON_PADDING_VERTICAL, BUTTON_PADDING_HORIZONTAL));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * Applies the secondary grey style to the given button.
     *
     * @param button the button to style
     */
    public static void styleSecondaryButton(JButton button) {
        button.setBackground(SECONDARY_BUTTON_BACKGROUND_COLOR);
        button.setForeground(SECONDARY_BUTTON_TEXT_COLOR);
        button.setFont(new Font(SANS_SERIF_FONT_FAMILY, Font.PLAIN, BUTTON_FONT_SIZE));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(
                BUTTON_PADDING_VERTICAL, BUTTON_PADDING_HORIZONTAL,
                BUTTON_PADDING_VERTICAL, BUTTON_PADDING_HORIZONTAL));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
}

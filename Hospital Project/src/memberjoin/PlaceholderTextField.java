package memberjoin;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
class PlaceholderTextField extends JTextField {

    private String placeholder;
    private Font placeholderFont;

    public PlaceholderTextField() {
    }

    public PlaceholderTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderTextField(final String pText) {
        super(pText);
    }

    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    public Font getPlaceholderFont() {
        return placeholderFont;
    }

    public void setPlaceholderFont(Font font) {
        placeholderFont = font;
        setFont(font);
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.GRAY);

        if (placeholderFont != null) {
            g.setFont(placeholderFont);
        }

        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent());
    }
}

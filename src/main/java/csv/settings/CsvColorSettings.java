package csv.settings;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import csv.CsvIcon;
import csv.CsvLanguage;
import csv.highlighter.CsvSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CsvColorSettings implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS;

    static {
        List<AttributesDescriptor> attributesDescriptors = new ArrayList();
        attributesDescriptors.add(new AttributesDescriptor("Separator", CsvSyntaxHighlighter.COMMA));
        attributesDescriptors.add(new AttributesDescriptor("Quote", CsvSyntaxHighlighter.QUOTE));
        attributesDescriptors.add(new AttributesDescriptor("Text", CsvSyntaxHighlighter.TEXT));
        attributesDescriptors.add(new AttributesDescriptor("Escaped Text", CsvSyntaxHighlighter.ESCAPED_TEXT));

        DESCRIPTORS = attributesDescriptors.toArray(new AttributesDescriptor[attributesDescriptors.size()]);
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return CsvIcon.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new CsvSyntaxHighlighter(null, null);
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "1,\"Hello, World!\",field1,3,5,44.44,77,aaaa,Some text,0.88\n" +
                "2,\"version 0.1 \"\"Cool\"\" abcd\",Russia,777,99.99,347.0,33.02,HHH,Dup,11.58\n" +
                "3,\"Some text, some text\",USSR,1996,13.25,0.00,78.0,dup,Some Name,0.0\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return CsvLanguage.INSTANCE.getDisplayName();
    }
}
// This is a generated file. Not intended for manual editing.
package csv.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static csv.psi.CsvTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import static csv.parser.CsvParserUtil.*;

public class CsvParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == FIELD) {
      r = field(b, 0);
    }
    else if (t == RECORD) {
      r = record(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return csvFile(b, l + 1);
  }

  /* ********************************************************** */
  // record (CRLF record)* [CRLF]
  static boolean csvFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csvFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = record(b, l + 1);
    r = r && csvFile_1(b, l + 1);
    r = r && csvFile_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (CRLF record)*
  private static boolean csvFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csvFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!csvFile_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "csvFile_1", c)) break;
    }
    return true;
  }

  // CRLF record
  private static boolean csvFile_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csvFile_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CRLF);
    r = r && record(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [CRLF]
  private static boolean csvFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "csvFile_2")) return false;
    consumeToken(b, CRLF);
    return true;
  }

  /* ********************************************************** */
  // QUOTE ( TEXT | ESCAPED_TEXT)* QUOTE
  static boolean escaped(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped")) return false;
    if (!nextTokenIs(b, QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE);
    r = r && escaped_1(b, l + 1);
    r = r && consumeToken(b, QUOTE);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( TEXT | ESCAPED_TEXT)*
  private static boolean escaped_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!escaped_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "escaped_1", c)) break;
    }
    return true;
  }

  // TEXT | ESCAPED_TEXT
  private static boolean escaped_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped_1_0")) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = consumeToken(b, ESCAPED_TEXT);
    return r;
  }

  /* ********************************************************** */
  // escaped | nonEscaped
  public static boolean field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = escaped(b, l + 1);
    if (!r) r = nonEscaped(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TEXT*
  static boolean nonEscaped(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nonEscaped")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, TEXT)) break;
      if (!empty_element_parsed_guard_(b, "nonEscaped", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // field ( << separator >> COMMA field)*
  public static boolean record(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RECORD, "<record>");
    r = field(b, l + 1);
    r = r && record_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( << separator >> COMMA field)*
  private static boolean record_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!record_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "record_1", c)) break;
    }
    return true;
  }

  // << separator >> COMMA field
  private static boolean record_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = separator(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}

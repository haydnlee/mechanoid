package com.robotoworks.mechanoid.db.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.robotoworks.mechanoid.db.generator.SqliteDatabaseSnapshot;
import com.robotoworks.mechanoid.db.sqliteModel.ActionStatement;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnDef;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSource;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnType;
import com.robotoworks.mechanoid.db.sqliteModel.ConfigBlock;
import com.robotoworks.mechanoid.db.sqliteModel.ConfigurationStatement;
import com.robotoworks.mechanoid.db.sqliteModel.ContentUri;
import com.robotoworks.mechanoid.db.sqliteModel.ContentUriParamSegment;
import com.robotoworks.mechanoid.db.sqliteModel.ContentUriSegment;
import com.robotoworks.mechanoid.db.sqliteModel.CreateTableStatement;
import com.robotoworks.mechanoid.db.sqliteModel.CreateViewStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DDLStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DatabaseBlock;
import com.robotoworks.mechanoid.db.sqliteModel.Model;
import com.robotoworks.mechanoid.db.sqliteModel.ResultColumn;
import com.robotoworks.mechanoid.db.util.ModelUtil;
import com.robotoworks.mechanoid.text.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ContentProviderContractGenerator {
  public CharSequence generate(final Model model, final SqliteDatabaseSnapshot snapshot) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Generated by Robotoworks Mechanoid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package ");
    String _packageName = model.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import android.net.Uri;");
    _builder.newLine();
    _builder.append("import android.provider.BaseColumns;");
    _builder.newLine();
    _builder.append("import com.robotoworks.mechanoid.Mechanoid;");
    _builder.newLine();
    _builder.append("import com.robotoworks.mechanoid.db.AbstractValuesBuilder;");
    _builder.newLine();
    _builder.append("import java.lang.reflect.Field;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    DatabaseBlock _database = model.getDatabase();
    String _name = _database.getName();
    String _pascalize = Strings.pascalize(_name);
    _builder.append(_pascalize, "");
    _builder.append("Contract  {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("public static final String CONTENT_AUTHORITY = initAuthority();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static String initAuthority() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String authority = \"");
    String _packageName_1 = model.getPackageName();
    _builder.append(_packageName_1, "		");
    _builder.append(".");
    DatabaseBlock _database_1 = model.getDatabase();
    String _name_1 = _database_1.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "		");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("ClassLoader loader = ");
    DatabaseBlock _database_2 = model.getDatabase();
    String _name_2 = _database_2.getName();
    String _pascalize_1 = Strings.pascalize(_name_2);
    _builder.append(_pascalize_1, "    		");
    _builder.append("Contract.class.getClassLoader();");
    _builder.newLineIfNotEmpty();
    _builder.append("    \t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Class<?> clz = loader.loadClass(\"");
    String _packageName_2 = model.getPackageName();
    _builder.append(_packageName_2, "			");
    _builder.append(".");
    DatabaseBlock _database_3 = model.getDatabase();
    String _name_3 = _database_3.getName();
    String _pascalize_2 = Strings.pascalize(_name_3);
    _builder.append(_pascalize_2, "			");
    _builder.append("ContentProviderAuthority\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("Field declaredField = clz.getDeclaredField(\"CONTENT_AUTHORITY\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("authority = declaredField.get(null).toString();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (ClassNotFoundException e) {} ");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("catch (NoSuchFieldException e) {} ");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("catch (IllegalArgumentException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (IllegalAccessException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return authority;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final Uri BASE_CONTENT_URI = Uri.parse(\"content://\" + CONTENT_AUTHORITY);");
    _builder.newLine();
    _builder.newLine();
    {
      Collection<CreateTableStatement> _tables = snapshot.getTables();
      for(final CreateTableStatement tbl : _tables) {
        _builder.append("\t");
        _builder.append("interface ");
        String _name_4 = tbl.getName();
        String _pascalize_3 = Strings.pascalize(_name_4);
        _builder.append(_pascalize_3, "	");
        _builder.append("Columns {");
        _builder.newLineIfNotEmpty();
        {
          EList<ColumnSource> _columnDefs = tbl.getColumnDefs();
          final Function1<ColumnSource,Boolean> _function = new Function1<ColumnSource,Boolean>() {
              public Boolean apply(final ColumnSource it) {
                String _name = it.getName();
                boolean _equals = _name.equals("_id");
                boolean _not = (!_equals);
                return Boolean.valueOf(_not);
              }
            };
          Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(_columnDefs, _function);
          for(final ColumnSource col : _filter) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("String ");
            String _name_5 = col.getName();
            String _underscore = Strings.underscore(_name_5);
            String _upperCase = _underscore.toUpperCase();
            _builder.append(_upperCase, "		");
            _builder.append(" = \"");
            String _name_6 = col.getName();
            _builder.append(_name_6, "		");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      Collection<CreateViewStatement> _views = snapshot.getViews();
      for(final CreateViewStatement vw : _views) {
        _builder.append("\t");
        _builder.append("interface ");
        String _name_7 = vw.getName();
        String _pascalize_4 = Strings.pascalize(_name_7);
        _builder.append(_pascalize_4, "	");
        _builder.append("Columns {");
        _builder.newLineIfNotEmpty();
        {
          ArrayList<ColumnSource> _viewResultColumns = ModelUtil.getViewResultColumns(vw);
          final Function1<ColumnSource,Boolean> _function_1 = new Function1<ColumnSource,Boolean>() {
              public Boolean apply(final ColumnSource it) {
                String _name = it.getName();
                boolean _equals = _name.equals("_id");
                boolean _not = (!_equals);
                return Boolean.valueOf(_not);
              }
            };
          Iterable<ColumnSource> _filter_1 = IterableExtensions.<ColumnSource>filter(_viewResultColumns, _function_1);
          for(final ColumnSource col_1 : _filter_1) {
            _builder.append("\t");
            _builder.append("\t");
            CharSequence _generateInterfaceMemberForResultColumn = this.generateInterfaceMemberForResultColumn(col_1);
            _builder.append(_generateInterfaceMemberForResultColumn, "		");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t");
    _builder.newLine();
    {
      Collection<CreateTableStatement> _tables_1 = snapshot.getTables();
      for(final CreateTableStatement tbl_1 : _tables_1) {
        _builder.append("\t");
        CharSequence _generateContractItem = this.generateContractItem(model, tbl_1);
        _builder.append(_generateContractItem, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      Collection<CreateViewStatement> _views_1 = snapshot.getViews();
      for(final CreateViewStatement vw_1 : _views_1) {
        _builder.append("\t");
        CharSequence _generateContractItem_1 = this.generateContractItem(model, vw_1);
        _builder.append(_generateContractItem_1, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateContractItemsForActions = this.generateContractItemsForActions(model, snapshot);
    _builder.append(_generateContractItemsForActions, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private ");
    DatabaseBlock _database_4 = model.getDatabase();
    String _name_8 = _database_4.getName();
    String _pascalize_5 = Strings.pascalize(_name_8);
    _builder.append(_pascalize_5, "	");
    _builder.append("Contract(){}");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateContractItemsForActions(final Model model, final SqliteDatabaseSnapshot snapshot) {
    StringConcatenation _builder = new StringConcatenation();
    {
      DatabaseBlock _database = model.getDatabase();
      ConfigBlock _config = _database.getConfig();
      boolean _notEquals = (!Objects.equal(_config, null));
      if (_notEquals) {
        {
          DatabaseBlock _database_1 = model.getDatabase();
          ConfigBlock _config_1 = _database_1.getConfig();
          EList<ConfigurationStatement> _statements = _config_1.getStatements();
          Iterable<ActionStatement> _filter = Iterables.<ActionStatement>filter(_statements, ActionStatement.class);
          final Function1<ActionStatement,Boolean> _function = new Function1<ActionStatement,Boolean>() {
              public Boolean apply(final ActionStatement it) {
                ContentUri _uri = it.getUri();
                String _type = _uri.getType();
                boolean _containsDefinition = snapshot.containsDefinition(_type);
                boolean _not = (!_containsDefinition);
                return Boolean.valueOf(_not);
              }
            };
          Iterable<ActionStatement> _filter_1 = IterableExtensions.<ActionStatement>filter(_filter, _function);
          for(final ActionStatement action : _filter_1) {
            _builder.append("public static class ");
            ContentUri _uri = action.getUri();
            String _type = _uri.getType();
            String _pascalize = Strings.pascalize(_type);
            _builder.append(_pascalize, "");
            _builder.append(" {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            CharSequence _createActionUriBuilderMethod = this.createActionUriBuilderMethod(action);
            _builder.append(_createActionUriBuilderMethod, "	");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("public static final String CONTENT_TYPE =");
            _builder.newLine();
            _builder.append("\t        ");
            _builder.append("\"vnd.android.cursor.dir/vnd.");
            DatabaseBlock _database_2 = model.getDatabase();
            String _name = _database_2.getName();
            String _lowerCase = _name.toLowerCase();
            _builder.append(_lowerCase, "	        ");
            _builder.append(".");
            ContentUri _uri_1 = action.getUri();
            String _type_1 = _uri_1.getType();
            _builder.append(_type_1, "	        ");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence createActionUriBuilderMethod(final ActionStatement action) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Uri build");
    String _name = action.getName();
    String _pascalize = Strings.pascalize(_name);
    _builder.append(_pascalize, "");
    _builder.append("Uri(");
    ContentUri _uri = action.getUri();
    String _methodArgs = this.toMethodArgs(_uri);
    _builder.append(_methodArgs, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return BASE_CONTENT_URI");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".buildUpon()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".appendPath(\"");
    ContentUri _uri_1 = action.getUri();
    String _type = _uri_1.getType();
    _builder.append(_type, "		");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    {
      ContentUri _uri_2 = action.getUri();
      EList<ContentUriSegment> _segments = _uri_2.getSegments();
      for(final ContentUriSegment seg : _segments) {
        {
          if ((seg instanceof ContentUriParamSegment)) {
            {
              boolean _isNum = ((ContentUriParamSegment) seg).isNum();
              if (_isNum) {
                _builder.append("\t\t");
                _builder.append(".appendPath(String.valueOf(");
                String _name_1 = seg.getName();
                String _camelize = Strings.camelize(_name_1);
                _builder.append(_camelize, "		");
                _builder.append("))");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                _builder.append(".appendPath(");
                String _name_2 = seg.getName();
                String _camelize_1 = Strings.camelize(_name_2);
                _builder.append(_camelize_1, "		");
                _builder.append(")");
                _builder.newLineIfNotEmpty();
              }
            }
          } else {
            _builder.append("\t\t");
            _builder.append(".appendPath(\"");
            String _name_3 = seg.getName();
            _builder.append(_name_3, "		");
            _builder.append("\")");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append(".build();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Find all actions associated to the given definition,
   * actions are associated to the definition via the first
   * part of an action uri, for instance /recipes/a/b/c is
   * associated to recipes
   */
  public Iterable<ActionStatement> findActionsForDefinition(final Model model, final String defName) {
    DatabaseBlock _database = model.getDatabase();
    ConfigBlock _config = _database.getConfig();
    EList<ConfigurationStatement> _statements = _config==null?(EList<ConfigurationStatement>)null:_config.getStatements();
    Iterable<ActionStatement> _filter = Iterables.<ActionStatement>filter(_statements, ActionStatement.class);
    final Function1<ActionStatement,Boolean> _function = new Function1<ActionStatement,Boolean>() {
        public Boolean apply(final ActionStatement action) {
          ContentUri _uri = action.getUri();
          String _type = _uri.getType();
          boolean _equals = _type.equals(defName);
          return Boolean.valueOf(_equals);
        }
      };
    return IterableExtensions.<ActionStatement>filter(_filter, _function);
  }
  
  public String toMethodArgs(final ContentUri uri) {
    EList<ContentUriSegment> _segments = uri.getSegments();
    Iterable<ContentUriParamSegment> _filter = Iterables.<ContentUriParamSegment>filter(_segments, ContentUriParamSegment.class);
    final Function1<ContentUriParamSegment,String> _function = new Function1<ContentUriParamSegment,String>() {
        public String apply(final ContentUriParamSegment seg) {
          boolean _isNum = seg.isNum();
          if (_isNum) {
            String _name = seg.getName();
            String _camelize = Strings.camelize(_name);
            return ("int " + _camelize);
          } else {
            String _name_1 = seg.getName();
            String _camelize_1 = Strings.camelize(_name_1);
            return ("String " + _camelize_1);
          }
        }
      };
    String _join = IterableExtensions.<ContentUriParamSegment>join(_filter, ", ", _function);
    return _join;
  }
  
  public boolean hasMethodArgs(final ContentUri uri) {
    EList<ContentUriSegment> _segments = uri.getSegments();
    Iterable<ContentUriParamSegment> _filter = Iterables.<ContentUriParamSegment>filter(_segments, ContentUriParamSegment.class);
    int _size = IterableExtensions.size(_filter);
    boolean _greaterThan = (_size > 0);
    return _greaterThan;
  }
  
  public CharSequence generateContractItem(final Model model, final DDLStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* <p>Column definitions and helper methods to work with the ");
    String _name = this.getName(stmt);
    String _pascalize = Strings.pascalize(_name);
    _builder.append(_pascalize, " ");
    _builder.append(".</p>");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static class ");
    String _name_1 = this.getName(stmt);
    String _pascalize_1 = Strings.pascalize(_name_1);
    _builder.append(_pascalize_1, "");
    _builder.append(" implements ");
    String _name_2 = this.getName(stmt);
    String _pascalize_2 = Strings.pascalize(_name_2);
    _builder.append(_pascalize_2, "");
    _builder.append("Columns");
    {
      boolean _hasAndroidPrimaryKey = this.hasAndroidPrimaryKey(stmt);
      if (_hasAndroidPrimaryKey) {
        _builder.append(", BaseColumns");
      }
    }
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("public static final Uri CONTENT_URI = ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("BASE_CONTENT_URI.buildUpon().appendPath(\"");
    String _name_3 = this.getName(stmt);
    String _lowerCase = _name_3.toLowerCase();
    _builder.append(_lowerCase, "			");
    _builder.append("\").build();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* <p>The content type for a cursor that contains many ");
    String _name_4 = this.getName(stmt);
    String _pascalize_3 = Strings.pascalize(_name_4);
    _builder.append(_pascalize_3, "	 ");
    _builder.append(" rows.</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static final String CONTENT_TYPE =");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("\"vnd.android.cursor.dir/vnd.");
    DatabaseBlock _database = model.getDatabase();
    String _name_5 = _database.getName();
    String _lowerCase_1 = _name_5.toLowerCase();
    _builder.append(_lowerCase_1, "            ");
    _builder.append(".");
    String _name_6 = this.getName(stmt);
    _builder.append(_name_6, "            ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      boolean _hasAndroidPrimaryKey_1 = this.hasAndroidPrimaryKey(stmt);
      if (_hasAndroidPrimaryKey_1) {
        _builder.append("\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* <p>The content type for a cursor that contains a single ");
        String _name_7 = this.getName(stmt);
        String _pascalize_4 = Strings.pascalize(_name_7);
        _builder.append(_pascalize_4, "	 ");
        _builder.append(" row.</p>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public static final String ITEM_CONTENT_TYPE =");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("\"vnd.android.cursor.item/vnd.");
        DatabaseBlock _database_1 = model.getDatabase();
        String _name_8 = _database_1.getName();
        String _lowerCase_2 = _name_8.toLowerCase();
        _builder.append(_lowerCase_2, "		");
        _builder.append(".");
        String _name_9 = this.getName(stmt);
        _builder.append(_name_9, "		");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* <p>Builds a Uri with appended id for a row in ");
    String _name_10 = this.getName(stmt);
    String _pascalize_5 = Strings.pascalize(_name_10);
    _builder.append(_pascalize_5, "	 ");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("* eg:- content://");
    String _packageName = model.getPackageName();
    _builder.append(_packageName, "	 ");
    _builder.append(".");
    DatabaseBlock _database_2 = model.getDatabase();
    String _name_11 = _database_2.getName();
    String _lowerCase_3 = _name_11.toLowerCase();
    _builder.append(_lowerCase_3, "	 ");
    _builder.append("/");
    String _name_12 = this.getName(stmt);
    String _lowerCase_4 = _name_12.toLowerCase();
    _builder.append(_lowerCase_4, "	 ");
    _builder.append("/123.</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static Uri buildUriWithId(long id) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    String _name_13 = this.getName(stmt);
    Iterable<ActionStatement> actions = this.findActionsForDefinition(model, _name_13);
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(actions, null));
      if (_notEquals) {
        {
          for(final ActionStatement action : actions) {
            _builder.append("\t");
            CharSequence _createActionUriBuilderMethod = this.createActionUriBuilderMethod(action);
            _builder.append(_createActionUriBuilderMethod, "	");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("public static int delete() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return Mechanoid.getContentResolver().delete(CONTENT_URI, null, null);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int delete(String where, String[] selectionArgs) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return Mechanoid.getContentResolver().delete(CONTENT_URI, where, selectionArgs);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* <p>Create a new Builder for ");
    String _name_14 = this.getName(stmt);
    String _pascalize_6 = Strings.pascalize(_name_14);
    _builder.append(_pascalize_6, "	 ");
    _builder.append("</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static Builder newBuilder() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new Builder();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* <p>Build and execute insert or update statements for ");
    String _name_15 = this.getName(stmt);
    String _pascalize_7 = Strings.pascalize(_name_15);
    _builder.append(_pascalize_7, "	 ");
    _builder.append(".</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* <p>Use {@link ");
    String _name_16 = this.getName(stmt);
    String _pascalize_8 = Strings.pascalize(_name_16);
    _builder.append(_pascalize_8, "	 ");
    _builder.append("#newBuilder()} to create new builder</p>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class Builder extends AbstractValuesBuilder {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("private Builder() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("super(Mechanoid.getApplicationContext(), CONTENT_URI);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateBuilderSetters = this.generateBuilderSetters(stmt);
    _builder.append(_generateBuilderSetters, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateBuilderSetters(final CreateTableStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ColumnSource> _columnDefs = stmt.getColumnDefs();
      final Function1<ColumnSource,Boolean> _function = new Function1<ColumnSource,Boolean>() {
          public Boolean apply(final ColumnSource it) {
            String _name = it.getName();
            boolean _equals = _name.equals("_id");
            boolean _not = (!_equals);
            return Boolean.valueOf(_not);
          }
        };
      Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(_columnDefs, _function);
      for(final ColumnSource item : _filter) {
        ColumnDef col = ((ColumnDef) item);
        _builder.newLineIfNotEmpty();
        _builder.append("public Builder set");
        String _name = col.getName();
        String _pascalize = Strings.pascalize(_name);
        _builder.append(_pascalize, "");
        _builder.append("(");
        ColumnType _type = col.getType();
        String _javaTypeName = ModelUtil.toJavaTypeName(_type);
        _builder.append(_javaTypeName, "");
        _builder.append(" value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("mValues.put(");
        String _name_1 = col.getName();
        String _underscore = Strings.underscore(_name_1);
        String _upperCase = _underscore.toUpperCase();
        _builder.append(_upperCase, "	");
        _builder.append(", value);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this;");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  protected CharSequence _generateBuilderSetters(final CreateViewStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    ArrayList<ColumnSource> cols = ModelUtil.getViewResultColumns(stmt);
    _builder.newLineIfNotEmpty();
    {
      final Function1<ColumnSource,Boolean> _function = new Function1<ColumnSource,Boolean>() {
          public Boolean apply(final ColumnSource it) {
            String _name = it.getName();
            boolean _equals = _name.equals("_id");
            boolean _not = (!_equals);
            return Boolean.valueOf(_not);
          }
        };
      Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(cols, _function);
      for(final ColumnSource item : _filter) {
        ResultColumn col = ((ResultColumn) item);
        _builder.newLineIfNotEmpty();
        ColumnType type = ModelUtil.getInferredColumnType(col);
        _builder.newLineIfNotEmpty();
        _builder.append("public Builder set");
        String _name = col.getName();
        String _pascalize = Strings.pascalize(_name);
        _builder.append(_pascalize, "");
        _builder.append("(");
        String _javaTypeName = ModelUtil.toJavaTypeName(type);
        _builder.append(_javaTypeName, "");
        _builder.append(" value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("mValues.put(");
        String _name_1 = stmt.getName();
        String _pascalize_1 = Strings.pascalize(_name_1);
        _builder.append(_pascalize_1, "	");
        _builder.append(".");
        String _name_2 = col.getName();
        String _underscore = Strings.underscore(_name_2);
        String _upperCase = _underscore.toUpperCase();
        _builder.append(_upperCase, "	");
        _builder.append(", value);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this;");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  protected String _getName(final CreateTableStatement stmt) {
    String _name = stmt.getName();
    return _name;
  }
  
  protected String _getName(final CreateViewStatement stmt) {
    String _name = stmt.getName();
    return _name;
  }
  
  protected boolean _hasAndroidPrimaryKey(final CreateTableStatement stmt) {
    boolean _hasAndroidPrimaryKey = ModelUtil.hasAndroidPrimaryKey(stmt);
    return _hasAndroidPrimaryKey;
  }
  
  protected boolean _hasAndroidPrimaryKey(final CreateViewStatement stmt) {
    boolean _hasAndroidPrimaryKey = ModelUtil.hasAndroidPrimaryKey(stmt);
    return _hasAndroidPrimaryKey;
  }
  
  public CharSequence createMethodArgsFromColumns(final CreateTableStatement tbl) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ColumnSource> _columnDefs = tbl.getColumnDefs();
      final Function1<ColumnSource,Boolean> _function = new Function1<ColumnSource,Boolean>() {
          public Boolean apply(final ColumnSource it) {
            String _name = it.getName();
            boolean _equals = _name.equals("_id");
            boolean _not = (!_equals);
            return Boolean.valueOf(_not);
          }
        };
      Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(_columnDefs, _function);
      boolean _hasElements = false;
      for(final ColumnSource item : _filter) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        ColumnDef col = ((ColumnDef) item);
        ColumnType _type = col.getType();
        String _javaTypeName = ModelUtil.toJavaTypeName(_type);
        _builder.append(_javaTypeName, "");
        _builder.append(" ");
        String _name = col.getName();
        String _camelize = Strings.camelize(_name);
        _builder.append(_camelize, "");
      }
    }
    return _builder;
  }
  
  public CharSequence generateInterfaceMemberForResultColumn(final ColumnSource expr) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      boolean _and_1 = false;
      String _name = expr.getName();
      boolean _notEquals = (!Objects.equal(_name, null));
      if (!_notEquals) {
        _and_1 = false;
      } else {
        String _name_1 = expr.getName();
        boolean _equals = _name_1.equals("");
        boolean _not = (!_equals);
        _and_1 = (_notEquals && _not);
      }
      if (!_and_1) {
        _and = false;
      } else {
        String _name_2 = expr.getName();
        boolean _equals_1 = _name_2.equals("_id");
        boolean _not_1 = (!_equals_1);
        _and = (_and_1 && _not_1);
      }
      if (_and) {
        _builder.append("String ");
        String _name_3 = expr.getName();
        String _underscore = Strings.underscore(_name_3);
        String _upperCase = _underscore.toUpperCase();
        _builder.append(_upperCase, "");
        _builder.append(" = \"");
        String _name_4 = expr.getName();
        _builder.append(_name_4, "");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateBuilderSetters(final DDLStatement stmt) {
    if (stmt instanceof CreateTableStatement) {
      return _generateBuilderSetters((CreateTableStatement)stmt);
    } else if (stmt instanceof CreateViewStatement) {
      return _generateBuilderSetters((CreateViewStatement)stmt);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(stmt).toString());
    }
  }
  
  public String getName(final DDLStatement stmt) {
    if (stmt instanceof CreateTableStatement) {
      return _getName((CreateTableStatement)stmt);
    } else if (stmt instanceof CreateViewStatement) {
      return _getName((CreateViewStatement)stmt);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(stmt).toString());
    }
  }
  
  public boolean hasAndroidPrimaryKey(final DDLStatement stmt) {
    if (stmt instanceof CreateTableStatement) {
      return _hasAndroidPrimaryKey((CreateTableStatement)stmt);
    } else if (stmt instanceof CreateViewStatement) {
      return _hasAndroidPrimaryKey((CreateViewStatement)stmt);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(stmt).toString());
    }
  }
}

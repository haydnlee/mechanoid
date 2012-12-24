/*
 * generated by Xtext
 */
package com.robotoworks.mechanoid.sqlite.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.robotoworks.mechanoid.sqlite.services.SqliteModelGrammarAccess;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class SqliteModelFormatter extends AbstractDeclarativeFormatter {
	
	@Override
	protected void configureFormatting(FormattingConfig c) {
		SqliteModelGrammarAccess g = (SqliteModelGrammarAccess) getGrammarAccess();

		c.setLinewrap(0, 1, 2).before(g.getSL_COMMENTRule());
		c.setLinewrap(0, 1, 2).before(g.getML_COMMENTRule());
		c.setLinewrap(0, 1, 1).after(g.getML_COMMENTRule());
				
		c.setLinewrap().after(g.getDDLStatementAccess().getLeftParenthesisKeyword_0_4());
		c.setLinewrap().after(g.getColumnDefRule());
	}
}

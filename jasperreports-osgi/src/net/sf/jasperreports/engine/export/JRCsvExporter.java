/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Contributors:
 * Mirko Wawrowsky - mawawrosky@users.sourceforge.net
 */
package net.sf.jasperreports.engine.export;

import java.io.IOException;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRStyledText;


/**
 * Exports a JasperReports document to CSV format. It has character output type and exports the document to a
 * grid-based layout.
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRCsvExporter.java 6089 2013-04-19 15:20:44Z lucianc $
 */
public class JRCsvExporter extends JRAbstractCsvExporter
{
	/**
	 * @see #JRCsvExporter(JasperReportsContext)
	 */
	public JRCsvExporter()
	{
		this(DefaultJasperReportsContext.getInstance());
	}

	
	/**
	 *
	 */
	public JRCsvExporter(JasperReportsContext jasperReportsContext)
	{
		super(jasperReportsContext);
	}

	
	/**
	 *
	 */
	protected void exportPage(JRPrintPage page) throws IOException
	{
		JRGridLayout layout = 
			new JRGridLayout(
				nature,
				page.getElements(), 
				jasperPrint.getPageWidth(), 
				jasperPrint.getPageHeight(), 
				globalOffsetX, 
				globalOffsetY,
				null //address
				);
		
		Grid grid = layout.getGrid();

		CutsInfo xCuts = layout.getXCuts();
		CutsInfo yCuts = layout.getYCuts();

		StringBuffer rowbuffer = null;
		
		String text = null;
		boolean isFirstColumn = true;
		int rowCount = grid.getRowCount();
		for(int y = 0; y < rowCount; y++)
		{
			rowbuffer = new StringBuffer();

			if (yCuts.isCutNotEmpty(y))
			{
				isFirstColumn = true;
				GridRow row = grid.getRow(y);
				int rowSize = row.size();
				for(int x = 0; x < rowSize; x++)
				{
					JRPrintElement element = row.get(x).getElement();
					if(element != null)
					{
						if (element instanceof JRPrintText)
						{
							JRStyledText styledText = getStyledText((JRPrintText)element);
							if (styledText == null)
							{
								text = "";
							}
							else
							{
								text = styledText.getText();
							}
							
							if (!isFirstColumn)
							{
								rowbuffer.append(delimiter);
							}
							rowbuffer.append(
								prepareText(text)
								);
							isFirstColumn = false;
						}
					}
					else
					{
						if (xCuts.isCutNotEmpty(x))
						{
							if (!isFirstColumn)
							{
								rowbuffer.append(delimiter);
							}
							isFirstColumn = false;
						}
					}
				}
				
				if (rowbuffer.length() > 0)
				{
					writer.write(rowbuffer.toString());
					writer.write(recordDelimiter);
				}
			}
		}
		
		if (progressMonitor != null)
		{
			progressMonitor.afterPageExport();
		}
	}

}
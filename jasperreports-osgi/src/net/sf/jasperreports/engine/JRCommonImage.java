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
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.type.ScaleImageEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRCommonImage.java 5878 2013-01-07 20:23:13Z teodord $
 */
public interface JRCommonImage extends JRCommonGraphicElement, JRBoxContainer
{

	/**
	 * Gets the image scale type.
	 * @return a value representing one of the scale type constants in {@link ScaleImageEnum}
	 */
	public ScaleImageEnum getScaleImageValue();
	
	/**
	 * Gets the image own scale type.
	 * @return a value representing one of the scale type constants in {@link ScaleImageEnum}
	 */
	public ScaleImageEnum getOwnScaleImageValue();
	
	/**
	 * Sets the image scale type.
	 * @param scaleImageEnum a value representing one of the scale type constants in {@link ScaleImageEnum}
	 */
	public void setScaleImage(ScaleImageEnum scaleImageEnum);

}

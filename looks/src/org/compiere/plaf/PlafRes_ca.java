/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.plaf;

import java.util.ListResourceBundle;

/**
 *  Translation Texts for Look & Feel
 *
 *  @author Jaume Teixi
 *  @version    $Id: PlafRes_ca.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class PlafRes_ca extends ListResourceBundle
{
	/** The data    */
	static final Object[][] contents = new String[][]
	{
	{ "BackColType",            "Tipus Color Fons" },
	{ "BackColType_Flat",       "Pla" },
	{ "BackColType_Gradient",   "Gradient" },
	{ "BackColType_Lines",      "L�nes" },
	{ "BackColType_Texture",    "Textura" },
	//
	{ "LookAndFeelEditor",      "Editor Aparen�a i Comportament" },
	{ "LookAndFeel",            "Aparen�a i Comportament" },
	{ "Theme",                  "Tema" },
	{ "EditAdempiereTheme",      "Editar Tema Adempiere" },
	{ "SetDefault",             "Fons Per Defecte" },
	{ "SetDefaultColor",        "Color Fons" },
	{ "ColorBlind",             "Defici�ncia Color" },
	{ "Example",                "Exemple" },
	{ "Reset",                  "Reiniciar" },
	{ "OK",                     "D'Acord" },
	{ "Cancel",                 "Cancel.lar" },
	//
	{ "AdempiereThemeEditor",    "Editor Tema Adempiere" },
	{ "MetalColors",            "Colors Metal" },
	{ "AdempiereColors",         "Colors Adempiere" },
	{ "AdempiereFonts",          "Fonts Adempiere" },
	{ "Primary1Info",           "Ombra, Separador" },
	{ "Primary1",               "Primari 1" },
	{ "Primary2Info",           "L�nia Focus, Men� Seleccionat" },
	{ "Primary2",               "Primari 2" },
	{ "Primary3Info",           "Taula Fila Seleccionada, Texte Seleccionat, Indicador Fons" },
	{ "Primary3",               "Primari 3" },
	{ "Secondary1Info",         "L�nies Marc" },
	{ "Secondary1",             "Secondari 1" },
	{ "Secondary2Info",         "Pestanyes Innactives, Camps Premuts, Texte + Marc Innactius" },
	{ "Secondary2",             "Secondari 2" },
	{ "Secondary3Info",         "Fons" },
	{ "Secondary3",             "Secondari 3" },
	//
	{ "ControlFontInfo",        "Font Control" },
	{ "ControlFont",            "Font Etiqueta" },
	{ "SystemFontInfo",         "Indicador, Nodes Arbre" },
	{ "SystemFont",             "Font Sistema" },
	{ "UserFontInfo",           "Dades Entrades Per l'Usuari" },
	{ "UserFont",               "Font Camp" },
//	{ "SmallFontInfo",          "Informes" },
	{ "SmallFont",              "Font Petita" },
	{ "WindowTitleFont",         "Font T�tol" },
	{ "MenuFont",               "Font Men�" },
	//
	{ "MandatoryInfo",          "Camp de Fons Obligatori" },
	{ "Mandatory",              "Obligatori" },
	{ "ErrorInfo",              "Camp de Fons Error" },
	{ "Error",                  "Error" },
	{ "InfoInfo",               "Camp de Fons Informaci�" },
	{ "Info",                   "Informaci�" },
	{ "WhiteInfo",              "L�nies" },
	{ "White",                  "Blanc" },
	{ "BlackInfo",              "L�nies, Text" },
	{ "Black",                  "Negre" },
	{ "InactiveInfo",           "Camp de Fons Innactiu" },
	{ "Inactive",               "Innactiu" },
	{ "TextOKInfo",             "Texte Superior OK" },
	{ "TextOK",                 "Texte - OK" },
	{ "TextIssueInfo",          "Texte Superior Error" },
	{ "TextIssue",              "Texte - Error" },
	//
	{ "FontChooser",            "Escollidor Font" },
	{ "Fonts",                  "Fonts" },
	{ "Plain",                  "Plana" },
	{ "Italic",                 "It�lica" },
	{ "Bold",                   "Negreta" },
	{ "BoldItalic",             "Negreta & It�lica" },
	{ "Name",                   "Nom" },
	{ "Size",                   "Tamany" },
	{ "Style",                  "Estil" },
	{ "TestString",             "Aix� �s nom�s una Prova! La Guineu marr� r�pida �st� fent quelcom. 12,3456.78 LetterLOne = l1 LetterOZero = O0" },
	{ "FontString",             "Font" },
	//
	{ "AdempiereColorEditor",    "Editor Color Adempiere" },
	{ "AdempiereType",           "Tipus Color" },
	{ "GradientUpperColor",     "Color Dalt Degradat" },
	{ "GradientLowerColor",     "Color Baix Degradat" },
	{ "GradientStart",          "Inici Degradat" },
	{ "GradientDistance",       "Dist�ncia Degradat" },
	{ "TextureURL",             "Textura URL" },
	{ "TextureAlpha",           "Textura Alfa" },
	{ "TextureTaintColor",      "Textura Color Corrupci�" },
	{ "LineColor",              "Color L�nia" },
	{ "LineBackColor",          "Color Fons" },
	{ "LineWidth",              "Ampla L�nia" },
	{ "LineDistance",           "Dist�ncia L�nia" },
	{ "FlatColor",              "Color Pla" }
	};

	/**
	 * Get Contents
	 * @return contents
	 */
	public Object[][] getContents()
	{
		return contents;
	}
}   //  Res

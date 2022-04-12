/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.apache.ecs;

/**
    This class defines all of the available Html Colors.

    @version $Id: HtmlColor.java,v 1.2 2006/07/30 00:54:02 jjanke Exp $
    @author <a href="mailto:snagy@servletapi.com">Stephan Nagy</a>
    @author <a href="mailto:jon@clearink.com">Jon S. Stevens</a>
*/
public abstract class HtmlColor
{

    // colors html 4.0

    /** HTML 4.0 specification for color <p style="color:#000000">BLACK</p>. */
    public final static String BLACK = "#000000";
    /** HTML 4.0 specification for color <p style="color:#C0C0C0">SILVER</p>. */
    public final static String SILVER = "#C0C0C0";
    /** HTML 4.0 specification for color <p style="color:#808080">GRAY</p>. */
    public final static String GRAY = "#808080";
    /** HTML 4.0 specification for color WHITE (not shown). */
    public final static String WHITE = "#FFFFFF";
    /** HTML 4.0 specification for color <p style="color:#800000">MAROON</p>. */
    public final static String MAROON = "#800000";
    /** HTML 4.0 specification for color <p style="color:#FF0000">RED</p>. */
    public final static String RED = "#FF0000";
    /** HTML 4.0 specification for color <p style="color:#800080">PURPLE</p>. */
    public final static String PURPLE = "#800080";
    /** HTML 4.0 specification for color <p style="color:#FF00FF">FUCHSIA</p>. */
    public final static String FUCHSIA = "#FF00FF";
    /** HTML 4.0 specification for color <p style="color:#008000">GREEN</p>. */
    public final static String GREEN = "#008000";
    /** HTML 4.0 specification for color <p style="color:#00FF00">LIME</p>. */
    public final static String LIME = "#00FF00";
    /** HTML 4.0 specification for color <p style="color:#808000">OLIVE</p>. */
    public final static String OLIVE = "#808000";
    /** HTML 4.0 specification for color <p style="color:#FFFF00">YELLOW</p>. */
    public final static String YELLOW = "#FFFF00";
    /** HTML 4.0 specification for color <p style="color:#000080">NAVY</p>. */
    public final static String NAVY = "#000080";
    /** HTML 4.0 specification for color <p style="color:#0000FF">BLUE</p>. */
    public final static String BLUE = "#0000FF";
    /** HTML 4.0 specification for color <p style="color:#008080">TEAL</p>. */
    public final static String TEAL = "#008080";
    /** HTML 4.0 specification for color <p style="color:#00FFFF">AQUA</p>. */
    public final static String AQUA = "#00FFFF";

    // colors html 3.0

    /** HTML 3.0 specification for color <p style="color:#EFF7FF">ALICEBLUEOC</p>. */
    public final static String ALICEBLUE = "#EFF7FF";
    /** HTML 3.0 specification for color <p style="color:#F9E8D2">ANTIQUEWHITE</p>. */
    public final static String ANTIQUEWHITE = "#F9E8D2";
    /** HTML 3.0 specification for color <p style="color:#43B7BA">AQUAMARINE</p>. */
    public final static String AQUAMARINE = "#43B7BA";
    /** HTML 3.0 specification for color <p style="color:#EFFFFF">AZURE</p>. */
    public final static String AZURE = "#EFFFFF";
    /** HTML 3.0 specification for color <p style="color:#F5F3D7">BEIGE</p>. */
    public final static String BEIGE = "#F5F3D7";
    /** HTML 3.0 specification for color <p style="color:#FDE0BC">BISQUE</p>. */
    public final static String BISQUE = "#FDE0BC";
    /** HTML 3.0 specification for color <p style="color:#FEE8C6">BLANCHEDALMOND</p>. */
    public final static String BLANCHEDALMOND = "#FEE8C6";
    /** HTML 3.0 specification for color <p style="color:#7931DF">BLUEVIOLET</p>. */
    public final static String BLUEVIOLET = "#7931DF";
    /** HTML 3.0 specification for color <p style="color:#980516">BROWN</p>. */
    public final static String BROWN = "#980516";
    /** HTML 3.0 specification for color <p style="color:#EABE83">BURLYWOOD</p>. */
    public final static String BURLYWOOD = "#EABE83";
    /** HTML 3.0 specification for color <p style="color:#578693">CADETBLUE</p>. */
    public final static String CADETBLUE = "#578693";
    /** HTML 3.0 specification for color <p style="color:#8AFB17">CHARTREUSE</p>. */
    public final static String CHARTREUSE = "#8AFB17";
    /** HTML 3.0 specification for color <p style="color:#C85A17">CHOCOLATE</p>. */
    public final static String CHOCOLATE = "#C85A17";
    /** HTML 3.0 specification for color <p style="color:#F76541">CORAL</p>. */
    public final static String CORAL = "#F76541";
    /** HTML 3.0 specification for color <p style="color:#151B8D">CORNFLOWERBLUE</p>. */
    public final static String CORNFLOWERBLUE = "#151B8D";
    /** HTML 3.0 specification for color <p style="color:#FFF7D7">CORNSILK</p>.*/
    public final static String CORNSILK = "#FFF7D7";
    /** HTML 3.0 specification for color <p style="color:#E41B17">CRIMSON</p>.
     *  RED2 couldn't find CRIMSON */
    public final static String CRIMSON = "#E41B17";
    /** HTML 3.0 specification for color <p style="color:#00FFFF">CYAN</p>. */
    public final static String CYAN = "#00FFFF";
    /** HTML 3.0 specification for color <p style="color:#2F2F4F">DARKBLUE</p>.
    *   MIDNIGHTBLUE couldn't find DARKBLUE */
    public final static String DARKBLUE = "#2F2F4F";
    /** HTML 3.0 specification for color <p style="color:#57FEFF">DARKCYAN</p>.
     *  CYAN1 couldn't find DARKCYAN */
    public final static String DARKCYAN = "#57FEFF";
    /** HTML 3.0 specification for color <p style="color:#AF7817">DARKGOLDENROD</p>. */
    public final static String DARKGOLDENROD = "#AF7817";
    /** HTML 3.0 specification for color <p style="color:#7A7777">DARKGRAY</p>.
     *  GRAY52 couldn't find DARKGRAY */
    public final static String DARKGRAY = "#7A7777";
    /** HTML 3.0 specification for color <p style="color:#254117">DARKGREEN</p>. */
    public final static String DARKGREEN = "#254117";
    /** HTML 3.0 specification for color <p style="color:#B7AD59">DARKKHAKI</p>. */
    public final static String DARKKHAKI = "#B7AD59";
    /** HTML 3.0 specification for color <p style="color:#F43EFF">DARKMAGENTA</p>.
     *  MAGENTA1 couldn't find DARKMAGENTA */
    public final static String DARKMAGENTA = "#F43EFF";
    /** HTML 3.0 specification for color <p style="color:#CCFB5D">DARKOLIVEGREEN</p>. */
    public final static String DARKOLIVEGREEN = "#CCFB5D";
    /** HTML 3.0 specification for color <p style="color:#F88017">DARKORANGE</p>. */
    public final static String DARKORANGE = "#F88017";
    /** HTML 3.0 specification for color <p style="color:#7D1B7E">DARKORCHID</p>. */
    public final static String DARKORCHID = "#7D1B7E";
    /** HTML 3.0 specification for color <p style="color:#E41B17">DARKRED</p>.
     *  RED2 couldn't find DARKRED */
    public final static String DARKRED = "#E41B17";
    /** HTML 3.0 specification for color <p style="color:#E18B6B">DARKSALMON</p>. */
    public final static String DARKSALMON = "#E18B6B";
    /** HTML 3.0 specification for color <p style="color:#8BB381">DARKSEAGREEN</p>. */
    public final static String DARKSEAGREEN = "#8BB381";
    /** HTML 3.0 specification for color <p style="color:#2B3856">DARKSLATEBLUE</p>. */
    public final static String DARKSLATEBLUE = "#2B3856";
    /** HTML 3.0 specification for color <p style="color:#253856">DARKSLATEGRAY</p>. */
    public final static String DARKSLATEGRAY = "#253856";
    /** HTML 3.0 specification for color <p style="color:#3B9C9C">DARKTURQUOISE</p>. */
    public final static String DARKTURQUOISE = "#3B9C9C";
    /** HTML 3.0 specification for color <p style="color:#842DCE">DARKVIOLET</p>. */
    public final static String DARKVIOLET = "#842DCE";
    /** HTML 3.0 specification for color <p style="color:#F52887">DEEPPINK</p>. */
    public final static String DEEPPINK = "#F52887";
    /** HTML 3.0 specification for color <p style="color:#3BB9FF">DEEPSKYBLUE</p>. */
    public final static String DEEPSKYBLUE = "#3BB9FF";
    /** HTML 3.0 specification for color <p style="color:#463E41">DIMGRAY</p>. */
    public final static String DIMGRAY = "#463E41";
    /** HTML 3.0 specification for color <p style="color:#1589FF">DODGERBLUE</p>. */
    public final static String DODGERBLUE = "#1589FF";
    /** HTML 3.0 specification for color <p style="color:#800517">FIREBRICK</p>. */
    public final static String FIREBRICK = "#800517";
    /** HTML 3.0 specification for color <p style="color:#FFF9EE">FLORALWHITE</p>. */
    public final static String FLORALWHITE = "#FFF9EE";
    /** HTML 3.0 specification for color <p style="color:#4E9258">FORESTGREEN</p>. */
    public final static String FORESTGREEN = "#4E9258";
    /** HTML 3.0 specification for color <p style="color:#D8D9D7">GAINSBORO</p>. */
    public final static String GAINSBORO = "#D8D9D7";
    /** HTML 3.0 specification for color <p style="color:#F7F7FF">GHOSTWHITE</p>. */
    public final static String GHOSTWHITE = "#F7F7FF";
    /** HTML 3.0 specification for color <p style="color:#D4A017">GOLD</p>. */
    public final static String GOLD = "#D4A017";
    /** HTML 3.0 specification for color <p style="color:#EDDA74">GOLDENROD</p>. */
    public final static String GOLDENROD = "#EDDA74";
    /** HTML 3.0 specification for color <p style="color:#B1FB17">GREENYELLOW</p>. */
    public final static String GREENYELLOW = "#B1FB17";
    /** HTML 3.0 specification for color <p style="color:#F0FEEE">HONEYDEW</p>. */
    public final static String HONEYDEW = "#F0FEEE";
    /** HTML 3.0 specification for color <p style="color:#5E2217">INDIANRED</p>. */
    public final static String INDIANRED = "#5E2217";
    /** HTML 3.0 specification for color <p style="color:#307D7E">INDIGO</p>.
     *  CYAN4 couldn't find INDIGO */
    public final static String INDIGO = "#307D7E";
    /** HTML 3.0 specification for color <p style="color:#FFFFEE">IVORY</p>. */
    public final static String IVORY = "#FFFFEE";
    /** HTML 3.0 specification for color <p style="color:#ADA96E">KHAKI</p>. */
    public final static String KHAKI = "#ADA96E";
    /** HTML 3.0 specification for color <p style="color:#E3E4FA">LAVENDER</p>. */
    public final static String LAVENDER = "#E3E4FA";
    /** HTML 3.0 specification for color <p style="color:#FDEEF4">LAVENDERBLUSH</p>. */
    public final static String LAVENDERBLUSH = "#FDEEF4";
    /** HTML 3.0 specification for color <p style="color:#87F717">LAWNGREEN</p>. */
    public final static String LAWNGREEN = "#87F717";
    /** HTML 3.0 specification for color <p style="color:#FFF8C6">LEMONCHIFFON</p>. */
    public final static String LEMONCHIFFON = "#FFF8C6";
    /** HTML 3.0 specification for color <p style="color:#ADDFFF">LIGHTBLUE</p>. */
    public final static String LIGHTBLUE = "#ADDFFF";
    /** HTML 3.0 specification for color <p style="color:#E77471">LIGHTCORAL</p>. */
    public final static String LIGHTCORAL = "#E77471";
    /** HTML 3.0 specification for color <p style="color:#E0FFFF">LIGHTCYAN</p>. */
    public final static String LIGHTCYAN = "#E0FFFF";
    /** HTML 3.0 specification for color <p style="color:#FAF8CC">LIGHTGOLDENROD</p>. */
    public final static String LIGHTGOLDENRODYELLOW = "#FAF8CC";
    /** HTML 3.0 specification for color <p style="color:#CCFFCC">LIGHTGREEN</p>.
     *  ARBITRARY SHADE OF GREEN couldnt find LIGHTGREEN */
    public final static String LIGHTGREEN = "#CCFFCC";
    /** HTML 3.0 specification for color <p style="color:#FAAFBA">LIGHPINK</p>. */
    public final static String LIGHTPINK = "#FAAFBA";
    /** HTML 3.0 specification for color <p style="color:#F9966B">LIGHTSALMON</p>. */
    public final static String LIGHTSALMON = "#F9966B";
    /** HTML 3.0 specification for color <p style="color:#3EA99F">LIGHTSEAGREEN</p>. */
    public final static String LIGHTSEAGREEN = "#3EA99F";
    /** HTML 3.0 specification for color <p style="color:#82CAFA">LIGHTSKYBLUE</p>. */
    public final static String LIGHTSKYBLUE = "#82CAFA";
    /** HTML 3.0 specification for color <p style="color:#6D7B8D">LIGHTSLATEGRAY</p>. */
    public final static String LIGHTSLATEGRAY = "#6D7B8D";
    /** HTML 3.0 specification for color <p style="color:#728FCE">LIGHTSTEELBLUE</p>. */
    public final static String LIGHTSTEELBLUE = "#728FCE";
    /** HTML 3.0 specification for color <p style="color:#FFFEDC">LIGHTYELLOW</p>. */
    public final static String LIGHTYELLOW = "#FFFEDC";
    /** HTML 3.0 specification for color <p style="color:#41A317">LIMEGREEN</p>. */
    public final static String LIMEGREEN = "#41A317";
    /** HTML 3.0 specification for color <p style="color:#F9EEE2">LINEN</p>. */
    public final static String LINEN = "#F9EEE2";
    /** HTML 3.0 specification for color <p style="color:#FF00FF">MAGENTA</p>. */
    public final static String MAGENTA = "#FF00FF";
    /** HTML 3.0 specification for color <p style="color:#348781">MEDIUMAQUAMARINE</p>. */
    public final static String MEDIUMAQUAMARINE = "#348781";
    /** HTML 3.0 specification for color <p style="color:#152DC6">MEDIUMBLUE</p>. */
    public final static String MEDIUMBLUE = "#152DC6";
    /** HTML 3.0 specification for color <p style="color:#B048B5">MEDIUMORCHID</p>. */
    public final static String MEDIUMORCHID = "#B048B5";
    /** HTML 3.0 specification for color <p style="color:#8467D7">MEDIUMPURPLE</p>. */
    public final static String MEDIUMPURPLE = "#8467D7";
    /** HTML 3.0 specification for color <p style="color:#306754">MEDIUMSEAGREEN</p>. */
    public final static String MEDIUMSEAGREEN = "#306754";
    /** HTML 3.0 specification for color <p style="color:#5E5A80">MEDIUMSLATEBLUE</p>. */
    public final static String MEDIUMSLATEBLUE = "#5E5A80";
    /** HTML 3.0 specification for color <p style="color:#348017">MEDIUMSPRINGGREEN</p>. */
    public final static String MEDIUMSPRINGGREEN = "#348017";
    /** HTML 3.0 specification for color <p style="color:#48CCCD">MEDIUMTURQUOISE</p>. */
    public final static String MEDIUMTURQUOISE = "#48CCCD";
    /** HTML 3.0 specification for color <p style="color:#CA226B">MEDIUMVIOLETRED</p>. */
    public final static String MEDIUMVIOLETRED = "#CA226B";
    /** HTML 3.0 specification for color <p style="color:#151B54">MIDNIGHTBLUE</p>. */
    public final static String MIDNIGHTBLUE = "#151B54";
    /** HTML 3.0 specification for color <p style="color:#F5FFF9">MINTCREAM</p>. */
    public final static String MINTCREAM = "#F5FFF9";
    /** HTML 3.0 specification for color <p style="color:#FDE1DD">MISTYROSE</p>. */
    public final static String MISTYROSE = "#FDE1DD";
    /** HTML 3.0 specification for color <p style="color:#FDE0AC">MOCCASIN</p>. */
    public final static String MOCCASIN = "#FDE0AC";
    /** HTML 3.0 specification for color <p style="color:#FDDAA3">NAVAJOWHITE</p>. */
    public final static String NAVAJOWHITE = "#FDDAA3";
    /** HTML 3.0 specification for color <p style="color:#FCF3E2">OLDLACE</p>. */
    public final static String OLDLACE = "#FCF3E2";
    /** HTML 3.0 specification for color <p style="color:#658017">OLIVEDRAB</p>. */
    public final static String OLIVEDRAB = "#658017";
    /** HTML 3.0 specification for color <p style="color:#F87A17">ORANGE</p>. */
    public final static String ORANGE = "#F87A17";
    /** HTML 3.0 specification for color <p style="color:#F63817">ORANGERED</p>. */
    public final static String ORANGERED = "#F63817";
    /** HTML 3.0 specification for color <p style="color:#E57DED">ORCHID</p>. */
    public final static String ORCHID = "#E57DED";
    /** HTML 3.0 specification for color <p style="color:#EDE49E">PALEGOLDENROD</p>. */
    public final static String PALEGOLDENROD = "#EDE49E";
    /** HTML 3.0 specification for color <p style="color:#AEEBEC">PALETURQUOISE</p>. */
    public final static String PALETURQUOISE = "#AEEBEC";
    /** HTML 3.0 specification for color <p style="color:#D16587">PALEVIOLETRED</p>. */
    public final static String PALEVIOLETRED = "#D16587";
    /** HTML 3.0 specification for color <p style="color:#FEECCF">PAPAYAWHIP</p>. */
    public final static String PAPAYAWHIP = "#FEECCF";
    /** HTML 3.0 specification for color <p style="color:#FCD5B0">PEACHPUFF</p>. */
    public final static String PEACHPUFF = "#FCD5B0";
    /** HTML 3.0 specification for color <p style="color:#C57726">PERU</p>. */
    public final static String PERU = "#C57726";
    /** HTML 3.0 specification for color <p style="color:#FAAFBE">PINK</p>. */
    public final static String PINK = "#FAAFBE";
    /** HTML 3.0 specification for color <p style="color:#B93B8F">PLUM</p>. */
    public final static String PLUM = "#B93B8F";
    /** HTML 3.0 specification for color <p style="color:#ADDCE3">POWDERBLUE</p>. */
    public final static String POWDERBLUE = "#ADDCE3";
    /** HTML 3.0 specification for color <p style="color:#B38481">ROSYBROWN</p>. */
    public final static String ROSYBROWN = "#B38481";
    /** HTML 3.0 specification for color <p style="color:#2B60DE">ROYALBLUE</p>. */
    public final static String ROYALBLUE = "#2B60DE";
    /** HTML 3.0 specification for color <p style="color:#F63526">SADDLEBROWN</p>.
     *  BROWN2 couldn't find SADDLEBROWN */
    public final static String SADDLEBROWN = "#F63526";
    /** HTML 3.0 specification for color <p style="color:#F88158">SALMON</p>. */
    public final static String SALMON = "#F88158";
    /** HTML 3.0 specification for color <p style="color:#EE9A4D">SANDYBROWN</p>. */
    public final static String SANDYBROWN = "#EE9A4D";
    /** HTML 3.0 specification for color <p style="color:#4E8975">SEAGREEN</p>. */
    public final static String SEAGREEN = "#4E8975";
    /** HTML 3.0 specification for color <p style="color:#FEF3EB">SEASHELL</p>. */
    public final static String SEASHELL = "#FEF3EB";
    /** HTML 3.0 specification for color <p style="color:#8A4117">SIENNA</p>. */
    public final static String SIENNA = "#8A4117";
    /** HTML 3.0 specification for color <p style="color:#6698FF">SKYBLUE</p>. */
    public final static String SKYBLUE = "#6698FF";
    /** HTML 3.0 specification for color <p style="color:#737CA1">SLATEBLUE</p>. */
    public final static String SLATEBLUE = "#737CA1";
    /** HTML 3.0 specification for color <p style="color:#657383">SLATEGRAY</p>. */
    public final static String SLATEGRAY = "#657383";
    /** HTML 3.0 specification for color <p style="color:#FFF9FA">SNOW</p>. */
    public final static String SNOW = "#FFF9FA";
    /** HTML 3.0 specification for color <p style="color:#4AA02C">SPRINGGREEN</p>. */
    public final static String SPRINGGREEN = "#4AA02C";
    /** HTML 3.0 specification for color <p style="color:#4863A0">STEELBLUE</p>. */
    public final static String STEELBLUE = "#4863A0";
    /** HTML 3.0 specification for color <p style="color:#D8AF79">TAN</p>. */
    public final static String TAN = "#D8AF79";
    /** HTML 3.0 specification for color <p style="color:#D2B9D3">THISTLE</p>. */
    public final static String THISTLE = "#D2B9D3";
    /** HTML 3.0 specification for color <p style="color:#F75431">TOMATO</p>. */
    public final static String TOMATO = "#F75431";
    /** HTML 3.0 specification for color <p style="color:#43C6DB">TURQUOISE</p>. */
    public final static String TURQUOISE = "#43C6DB";
    /** HTML 3.0 specification for color <p style="color:#8D38C9">VIOLET</p>. */
    public final static String VIOLET = "#8D38C9";
    /** HTML 3.0 specification for color <p style="color:#F3DAA9">WHEAT</p>. */
    public final static String WHEAT = "#F3DAA9";
    /** HTML 3.0 specification for color WHITESMOKE (not shown).
     *  WHITE couldn't find WHITESMOKE */
    public final static String WHITESMOKE = "#FFFFFF";
    /** HTML 3.0 specification for color <p style="color:#52D017"></p>. */
    public final static String YELLOWGREEN = "#52D017";

    // lowercase for compatability colors HTML 4.0

    /** Lowercase for compatiblity.
     *  @see #BLACK */
    public final static String black = BLACK;
    /** Lowercase for compatiblity.
     *  @see #SILVER */
    public final static String silver = SILVER;
    /** Lowercase for compatiblity.
     *  @see #GRAY */
    public final static String gray = GRAY;
    /** Lowercase for compatiblity.
     *  @see #WHITE */
    public final static String white = WHITE;
    /** Lowercase for compatiblity.
     *  @see #MAROON */
    public final static String maroon = MAROON;
    /** Lowercase for compatiblity.
     *  @see #RED */
    public final static String red = RED;
    /** Lowercase for compatiblity.
     *  @see #PURPLE */
    public final static String purple = PURPLE;
    /** Lowercase for compatiblity.
     *  @see #FUCHSIA */
    public final static String fuchsia = FUCHSIA;
    /** Lowercase for compatiblity.
     *  @see #GREEN */
    public final static String green = GREEN;
    /** Lowercase for compatiblity.
     *  @see #LIME */
    public final static String lime = LIME;
    /** Lowercase for compatiblity.
     *  @see #OLIVE */
    public final static String olive = OLIVE;
    /** Lowercase for compatiblity.
     *  @see #YELLOW */
    public final static String yellow = YELLOW;
    /** Lowercase for compatiblity.
     *  @see #NAVY */
    public final static String navy = NAVY;
    /** Lowercase for compatiblity.
     *  @see #BLUE */
    public final static String blue = BLUE;
    /** Lowercase for compatiblity.
     *  @see #TEAL */
    public final static String teal = TEAL;
    /** Lowercase for compatiblity.
     *  @see #AQUA */
    public final static String aqua = AQUA;

    // lowercase for compatability colors html 3.0

    /** Lowercase for compatiblity.
     *  @see #ALICEBLUE */
    public final static String aliceblue = ALICEBLUE;
    /** Lowercase for compatiblity.
     *  @see #ANTIQUEWHITE */
    public final static String antiquewhite = ANTIQUEWHITE;
    /** Lowercase for compatiblity.
     *  @see #AQUAMARINE */
    public final static String aquamarine = AQUAMARINE;
    /** Lowercase for compatiblity.
     *  @see #AZURE */
    public final static String azure = AZURE;
    /** Lowercase for compatiblity.
     *  @see #BEIGE */
    public final static String beige = BEIGE;
    /** Lowercase for compatiblity.
     *  @see #BISQUE */
    public final static String bisque = BISQUE;
    /** Lowercase for compatiblity.
     *  @see #BLANCHEDALMOND */
    public final static String blanchedalmond = BLANCHEDALMOND;
    /** Lowercase for compatiblity.
     *  @see #BLUEVIOLET */
    public final static String blueviolet = BLUEVIOLET;
    /** Lowercase for compatiblity.
     *  @see #BROWN */
    public final static String brown = BROWN;
    /** Lowercase for compatiblity.
     *  @see #BURLYWOOD */
    public final static String burlywood = BURLYWOOD;
    /** Lowercase for compatiblity.
     *  @see #CADETBLUE */
    public final static String cadetblue = CADETBLUE;
    /** Lowercase for compatiblity.
     *  @see #CHARTREUSE */
    public final static String chartreuse = CHARTREUSE;
    /** Lowercase for compatiblity.
     *  @see #CHOCOLATE */
    public final static String chocolate = CHOCOLATE;
    /** Lowercase for compatiblity.
     *  @see #CORAL */
    public final static String coral = CORAL;
    /** Lowercase for compatiblity.
     *  @see #CORNFLOWERBLUE */
    public final static String cornfolowerblue = CORNFLOWERBLUE;
    /** Lowercase for compatiblity.
     *  @see #CORNSILK */
    public final static String cornsilk = CORNSILK;
    /** Lowercase for compatiblity.
     *  @see #CRIMSON */
    public final static String crimson = CRIMSON;
    /** Lowercase for compatiblity.
     *  @see #CYAN */
    public final static String cyan = CYAN;
    /** Lowercase for compatiblity.
     *  @see #DARKBLUE */
    public final static String darkblue = DARKBLUE;
    /** Lowercase for compatiblity.
     *  @see #DARKCYAN */
    public final static String darkcyan = DARKCYAN;
    /** Lowercase for compatiblity.
     *  @see #DARKGOLDENROD */
    public final static String darkgoldenrod = DARKGOLDENROD;
    /** Lowercase for compatiblity.
     *  @see #DARKGRAY */
    public final static String darkgray = DARKGRAY;
    /** Lowercase for compatiblity.
     *  @see #DARKGREEN */
    public final static String darkgreen = DARKGREEN;
    /** Lowercase for compatiblity.
     *  @see #DARKKHAKI */
    public final static String darkkahki = DARKKHAKI;
    /** Lowercase for compatiblity.
     *  @see #DARKMAGENTA */
    public final static String darkmagenta = DARKMAGENTA;
    /** Lowercase for compatiblity.
     *  @see #DARKOLIVEGREEN */
    public final static String darkolivegreen = DARKOLIVEGREEN;
    /** Lowercase for compatiblity.
     *  @see #DARKORANGE */
    public final static String darkorange = DARKORANGE;
    /** Lowercase for compatiblity.
     *  @see #DARKORCHID */
    public final static String darkorchid = DARKORCHID;
    /** Lowercase for compatiblity.
     *  @see #DARKRED */
    public final static String darkred = DARKRED;
    /** Lowercase for compatiblity.
     *  @see #DARKSALMON */
    public final static String darksalmon = DARKSALMON;
    /** Lowercase for compatiblity.
     *  @see #DARKSEAGREEN */
    public final static String darkseagreen = DARKSEAGREEN;
    /** Lowercase for compatiblity.
     *  @see #DARKSLATEBLUE */
    public final static String darkslateblue = DARKSLATEBLUE;
    /** Lowercase for compatiblity.
     *  @see #DARKSLATEGRAY */
    public final static String darkslategray = DARKSLATEGRAY;
    /** Lowercase for compatiblity.
     *  @see #DARKTURQUOISE */
    public final static String darkturquoise = DARKTURQUOISE;
    /** Lowercase for compatiblity.
     *  @see #DARKVIOLET */
    public final static String darkviolet = DARKVIOLET;
    /** Lowercase for compatiblity.
     *  @see #DEEPPINK */
    public final static String deeppink = DEEPPINK;
    /** Lowercase for compatiblity.
     *  @see #DEEPSKYBLUE */
    public final static String deepskyblue = DEEPSKYBLUE;
    /** Lowercase for compatiblity.
     *  @see #DIMGRAY */
    public final static String dimgray = DIMGRAY;
    /** Lowercase for compatiblity.
     *  @see #DODGERBLUE */
    public final static String dodgerblue = DODGERBLUE;
    /** Lowercase for compatiblity.
     *  @see #FIREBRICK */
    public final static String firebrick = FIREBRICK;
    /** Lowercase for compatiblity.
     *  @see #FLORALWHITE */
    public final static String floralwhite = FLORALWHITE;
    /** Lowercase for compatiblity.
     *  @see #FORESTGREEN */
    public final static String forestgreen = FORESTGREEN;
    /** Lowercase for compatiblity.
     *  @see #GAINSBORO */
    public final static String gainsboro = GAINSBORO;
    /** Lowercase for compatiblity.
     *  @see #GHOSTWHITE */
    public final static String ghostwhite = GHOSTWHITE;
    /** Lowercase for compatiblity.
     *  @see #GOLD */
    public final static String gold = GOLD;
    /** Lowercase for compatiblity.
     *  @see #GOLDENROD */
    public final static String goldenrod = GOLDENROD;
    /** Lowercase for compatiblity.
     *  @see #GREENYELLOW */
    public final static String greenyellow = GREENYELLOW;
    /** Lowercase for compatiblity.
     *  @see #HONEYDEW */
    public final static String honeydew = HONEYDEW;
    /** Lowercase for compatiblity.
     *  @see #INDIANRED */
    public final static String indianred = INDIANRED;
    /** Lowercase for compatiblity.
     *  @see #INDIGO */
    public final static String indigo = INDIGO;
    /** Lowercase for compatiblity.
     *  @see #IVORY */
    public final static String ivory = IVORY;
    /** Lowercase for compatiblity.
     *  @see #KHAKI */
    public final static String khaki = KHAKI;
    /** Lowercase for compatiblity.
     *  @see #LAVENDER */
    public final static String lavender = LAVENDER;
    /** Lowercase for compatiblity.
     *  @see #LAVENDERBLUSH */
    public final static String lavenderblush = LAVENDERBLUSH;
    /** Lowercase for compatiblity.
     *  @see #LAWNGREEN */
    public final static String lawngreen = LAWNGREEN;
    /** Lowercase for compatiblity.
     *  @see #LEMONCHIFFON */
    public final static String lemmonchiffon = LEMONCHIFFON;
    /** Lowercase for compatiblity.
     *  @see #LIGHTBLUE */
    public final static String lightblue = LIGHTBLUE;
    /** Lowercase for compatiblity.
     *  @see #LIGHTCORAL */
    public final static String lightcoral = LIGHTCORAL;
    /** Lowercase for compatiblity.
     *  @see #LIGHTCYAN */
    public final static String lightcyan = LIGHTCYAN;
    /** Lowercase for compatiblity.
     *  @see #LIGHTGOLDENRODYELLOW */
    public final static String lightgoldenrodyellow = LIGHTGOLDENRODYELLOW;
    /** Lowercase for compatiblity.
     *  @see #LIGHTGREEN */
    public final static String lightgreen = LIGHTGREEN;
    /** Lowercase for compatiblity.
     *  @see #LIGHTPINK */
    public final static String lightpink = LIGHTPINK;
    /** Lowercase for compatiblity.
     *  @see #LIGHTSALMON */
    public final static String lightsalmon = LIGHTSALMON;
    /** Lowercase for compatiblity.
     *  @see #LIGHTSEAGREEN */
    public final static String lightseagreen = LIGHTSEAGREEN;
    /** Lowercase for compatiblity.
     *  @see #LIGHTSKYBLUE */
    public final static String lightskyblue = LIGHTSKYBLUE;
    /** Lowercase for compatiblity.
     *  @see #LIGHTSLATEGRAY */
    public final static String lightslategray = LIGHTSLATEGRAY;
    /** Lowercase for compatiblity.
     *  @see #LIGHTSTEELBLUE */
    public final static String lightsteelblue = LIGHTSTEELBLUE;
    /** Lowercase for compatiblity.
     *  @see #LIGHTYELLOW */
    public final static String lightyellow = LIGHTYELLOW;
    /** Lowercase for compatiblity.
     *  @see #LIMEGREEN */
    public final static String limegreen = LIMEGREEN;
    /** Lowercase for compatiblity.
     *  @see #LINEN */
    public final static String linen = LINEN;
    /** Lowercase for compatiblity.
     *  @see #MAGENTA */
    public final static String magenta = MAGENTA;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMAQUAMARINE */
    public final static String mediumaquamarine = MEDIUMAQUAMARINE;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMBLUE */
    public final static String mediumblue = MEDIUMBLUE;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMORCHID */
    public final static String mediumorchid = MEDIUMORCHID;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMPURPLE */
    public final static String mediumpurple = MEDIUMPURPLE;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMSEAGREEN */
    public final static String mediumseagreen = MEDIUMSEAGREEN;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMSLATEBLUE */
    public final static String mediumslateblue = MEDIUMSLATEBLUE;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMSPRINGGREEN */
    public final static String mediumspringgreen = MEDIUMSPRINGGREEN;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMTURQUOISE */
    public final static String mediumturquoise = MEDIUMTURQUOISE;
    /** Lowercase for compatiblity.
     *  @see #MEDIUMVIOLETRED */
    public final static String mediumvioletred = MEDIUMVIOLETRED;
    /** Lowercase for compatiblity.
     *  @see #MIDNIGHTBLUE */
    public final static String midnightblue = MIDNIGHTBLUE;
    /** Lowercase for compatiblity.
     *  @see #MINTCREAM */
    public final static String mintcream = MINTCREAM;
    /** Lowercase for compatiblity.
     *  @see #MISTYROSE */
    public final static String mistyrose = MISTYROSE;
    /** Lowercase for compatiblity.
     *  @see #MOCCASIN */
    public final static String moccasin = MOCCASIN;
    /** Lowercase for compatiblity.
     *  @see #NAVAJOWHITE */
    public final static String navajowhite = NAVAJOWHITE;
    /** Lowercase for compatiblity.
     *  @see #OLDLACE */
    public final static String oldlace = OLDLACE;
    /** Lowercase for compatiblity.
     *  @see #OLIVEDRAB */
    public final static String olivedrab = OLIVEDRAB;
    /** Lowercase for compatiblity.
     *  @see #ORANGE */
    public final static String orange = ORANGE;
    /** Lowercase for compatiblity.
     *  @see #ORANGERED */
    public final static String orangered = ORANGERED;
    /** Lowercase for compatiblity.
     *  @see #ORCHID */
    public final static String orchid = ORCHID;
    /** Lowercase for compatiblity.
     *  @see #PALEGOLDENROD */
    public final static String palegoldenrod = PALEGOLDENROD;
    /** Lowercase for compatiblity.
     *  @see #PALETURQUOISE */
    public final static String paleturquoise = PALETURQUOISE;
    /** Lowercase for compatiblity.
     *  @see #PALEVIOLETRED */
    public final static String palevioletred = PALEVIOLETRED;
    /** Lowercase for compatiblity.
     *  @see #PAPAYAWHIP */
    public final static String papayawhip = PAPAYAWHIP;
    /** Lowercase for compatiblity.
     *  @see #PEACHPUFF */
    public final static String peachpuff = PEACHPUFF;
    /** Lowercase for compatiblity.
     *  @see #PERU */
    public final static String peru = PERU;
    /** Lowercase for compatiblity.
     *  @see #PINK */
    public final static String pink = PINK;
    /** Lowercase for compatiblity.
     *  @see #PLUM */
    public final static String plum = PLUM;
    /** Lowercase for compatiblity.
     *  @see #POWDERBLUE */
    public final static String powderblue = POWDERBLUE;
    /** Lowercase for compatiblity.
     *  @see #ROSYBROWN */
    public final static String rosybrown = ROSYBROWN;
    /** Lowercase for compatiblity.
     *  @see #ROYALBLUE */
    public final static String royalblue = ROYALBLUE;
    /** Lowercase for compatiblity.
     *  @see #SADDLEBROWN */
    public final static String saddlebrown = SADDLEBROWN;
    /** Lowercase for compatiblity.
     *  @see #SALMON */
    public final static String salmon = SALMON;
    /** Lowercase for compatiblity.
     *  @see #SANDYBROWN */
    public final static String sandybrown = SANDYBROWN;
    /** Lowercase for compatiblity.
     *  @see #SEAGREEN */
    public final static String seagreen = SEAGREEN;
    /** Lowercase for compatiblity.
     *  @see #SEASHELL */
    public final static String seashell = SEASHELL;
    /** Lowercase for compatiblity.
     *  @see #SIENNA */
    public final static String sienna = SIENNA;
    /** Lowercase for compatiblity.
     *  @see #SKYBLUE */
    public final static String skyblue = SKYBLUE;
    /** Lowercase for compatiblity.
     *  @see #SLATEBLUE */
    public final static String slateblue = SLATEBLUE;
    /** Lowercase for compatiblity.
     *  @see #SLATEGRAY */
    public final static String slategray = SLATEGRAY;
    /** Lowercase for compatiblity.
     *  @see #SNOW */
    public final static String snow = SNOW;
    /** Lowercase for compatiblity.
     *  @see #SPRINGGREEN */
    public final static String springgreen = SPRINGGREEN;
    /** Lowercase for compatiblity.
     *  @see #STEELBLUE */
    public final static String steelblue = STEELBLUE;
    /** Lowercase for compatiblity.
     *  @see #TAN */
    public final static String tan = TAN;
    /** Lowercase for compatiblity.
     *  @see #THISTLE */
    public final static String thistle = THISTLE;
    /** Lowercase for compatiblity.
     *  @see #TOMATO */
    public final static String tomato = TOMATO;
    /** Lowercase for compatiblity.
     *  @see #TURQUOISE */
    public final static String turquoise = TURQUOISE;
    /** Lowercase for compatiblity.
     *  @see #VIOLET */
    public final static String violet = VIOLET;
    /** Lowercase for compatiblity.
     *  @see #WHEAT */
    public final static String wheat = WHEAT;
    /** Lowercase for compatiblity.
     *  @see #WHITESMOKE */
    public final static String whitesmoke = WHITESMOKE;
    /** Lowercase for compatiblity.
     *  @see #YELLOWGREEN */
    public final static String yellowgreen = YELLOWGREEN;
    
    /**
     * 	Convert Color.
     * 	Limited use as it assumes that color is hex string w/o #
        This method will take a string of hex values and 
        append a # to the beginning if it isn't already there.
        @param color the string to convert
	@return the converted string
    */
    public static String convertColor(String color)
    {
    	if (color == null)
    		return BLACK;
    	//	Hex 000000 w/o #
    	if (color.length() != 6)
    		return color;
        if (!color.startsWith("#"))
            return "#" + color;
        return color;
    }

     /**
       This method will take a string matching one of the colors
       defined in this class and return the string value of that
       color.
       @param color the color to get retrieve
       @return the string value of the color, if found, null otherwise
     */
     public static String getColor(String color)
     {
       Object colObject;
       try {
          colObject = Class.forName("org.apache.ecs.HtmlColor")
                 .getField(color)
                 .get(null);
         } catch (Exception e) {
             return null;
         }
         return((String)colObject);
     }
}

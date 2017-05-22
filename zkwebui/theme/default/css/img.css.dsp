<%@ page contentType="text/css;charset=UTF-8" %> 
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %> 
<%-- norm.css.dsp - 18 --%>

.field-text{
	height: 75px;
}

.field-text .z-textbox{
	height: 70px;
	background-color:#ffffff; 
	background-image:none;
}

.field-longtext{
	height:105px;
}
.field-longtext .z-textbox{
	height: 100px;
	background-color:#ffffff; 
	background-image:none;
}

.field-memo{
	height:405px;
}

.field-memo .z-textbox{
	height: 400px;
}

<%-- --%>

.normal-field .z-combobox-inp, 
.normal-field .z-datebox-inp,
.normal-field .z-timebox-inp, 
.normal-field .z-textbox,
.normal-field .z-intbox, 
.normal-field .z-longbox,
.normal-field .z-doublebox,
.normal-field .z-decimalbox
{
	background-color:#ffffff; 
	background-image:none;
}



<%-- --%>


.disabled-field .z-combobox-inp, 
.disabled-field .z-datebox-inp,
.disabled-field .z-timebox-inp, 
.disabled-field .z-textbox,
.disabled-field .z-intbox, 
.disabled-field .z-longbox,
.disabled-field .z-doublebox,
.disabled-field .z-decimalbox

{
	background-color:#eceae4; 
	background-image:none;
}


.readonly-field .z-combobox-inp, .readonly-field .z-datebox-inp,
.readonly-field .z-timebox-inp, .readonly-field .z-textbox,
.readonly-field .z-intbox, 
.readonly-field .z-longbox,
.readonly-field .z-doublebox,
.readonly-field .z-decimalbox

{
	background-color:#eceae4; 
	background-image:none;
}

.mandatory-field-label {
	text-decoration: none;  vertical-align: top; color:black;
	font-weight: bold;
}

.mandatory-field .z-combobox-inp, .mandatory-field .z-datebox-inp,
.mandatory-field .z-timebox-inp, .mandatory-field .z-textbox,
.mandatory-field .z-intbox, 
.mandatory-field .z-longbox,
.mandatory-field .z-doublebox,
.mandatory-field .z-decimalbox

{
	background-color:#ffffcc; 
	background-image:none;
}

tr.z-vbox-sep{
	height: 0; 
	padding: 0; 
	margin: 0;
}

.z-upload-icon {
	background-image: url(../images/zk/prgmeter.png);
}
.z-loading-icon {
	background-image: url(../images/zk/progress2.gif);
}
.z-shadow .z-shadow-cl{
	background-image: url(../images/zul/shadow-cl.png);
}
.z-shadow .z-shadow-cr{
	background-image: url(../images/zul/shadow-cr.png);
}
.z-shadow .z-shadow-cm {
	background-image: url(../images/zul/shadow-m.png);
}
.z-shadow .z-shadow-tl,
.z-shadow .z-shadow-tr{
	background-image:url(../images/zul/shadow-tlr.png);
}
.z-shadow .z-shadow-bl,
.z-shadow .z-shadow-br{
	background-image:url(../images/zul/shadow-blr.png);
}
span.z-drop-allow {
	background-image: url(../images/zul/misc/drag.png);
}
span.z-drop-disallow {
	background-image: url(../images/zul/misc/drag.png);
}
div.z-drop-cnt {
	background-image: url(../images/zul/misc/drop-bg.gif);
}
.z-fileupload-add {
	background-image: url(../images/zul/misc/fileupload.gif);
}
.z-fileupload-delete {
	background-image: url(../images/zul/misc/fileupload.gif);
}
.z-msgbox-question {
	background-image: url(../images/zul/msgbox/question-btn.png);
}
.z-msgbox-exclamation {
	background-image: url(../images/zul/msgbox/warning-btn.png);
}
.z-msgbox-imformation {
	background-image: url(../images/zul/msgbox/info-btn.png);
}
.z-msgbox-error {
	background-image: url(../images/zul/msgbox/stop-btn.png);
}
div.z-progressmeter {
	background-image: url(../images/zk/prgmeter_bg.png);
}
span.z-progressmeter-img {
	background-image: url(../images/zk/prgmeter.png);
}
<%-- normie.css.dsp - 47 --%>
span.z-drop-allow,
span.z-drop-disallow {
	background-image: url(../images/zul/misc/drag.gif);
}
.z-groupbox-tl {
	background-image:url(../images/zul/groupbox/groupbox-corner.gif);
}
.z-groupbox-tr{
	background-image:url(../images/zul/groupbox/groupbox-corner.gif);
}
.z-groupbox-hl {
	background-image:url(../images/zul/groupbox/groupbox-hl.gif);
}
.z-groupbox-hr {
	background-image:url(../images/zul/groupbox/groupbox-hr.gif);
}
.z-groupbox-hm {
	background-image:url(../images/zul/groupbox/groupbox-hm.gif);
}
.z-msgbox-question {
	background-image: url(../images/zul/msgbox/question-btn.gif);
}
.z-msgbox-exclamation {
	background-image: url(../images/zul/msgbox/warning-btn.gif);
}
.z-msgbox-imformation {
	background-image: url(../images/zul/msgbox/info-btn.gif);
}
.z-msgbox-error {
	background-image: url(../images/zul/msgbox/stop-btn.gif);
}
.z-splitter-ver-btn-l, .z-splitter-hor-btn-l {
	background-image: url(../images/zul/splt/colps-l.gif);
}
.z-splitter-ver-btn-r, .z-splitter-hor-btn-r {
	background-image: url(../images/zul/splt/colps-r.gif);
}
.z-splitter-ver-btn-t, .z-splitter-hor-btn-t {
	background-image: url(../images/zul/splt/colps-t.gif);
}
.z-splitter-ver-btn-b, .z-splitter-hor-btn-b {
	background-image: url(../images/zul/splt/colps-b.gif);
}
.z-columns-menu-grouping .z-menu-item-img {
	background-image:  url(../images/zul/grid/menu-group.gif);
}
.z-columns-menu-asc .z-menu-item-img {
	background-image:  url(../images/zul/grid/menu-arrowup.gif);
}
.z-columns-menu-dsc .z-menu-item-img {
	background-image:  url(../images/zul/grid/menu-arrowdown.gif);
}

.z-paging-btn .z-paging-next,
.z-paging-btn .z-paging-prev,
.z-paging-btn .z-paging-last,
.z-paging-btn .z-paging-first {
	background-image:url(../images/zul/paging/pg-btn.gif);
}
.z-panel-tl,
.z-panel-tr,
.z-panel-bl,
.z-panel-br {
	background-image:url(../images/zul/wnd/panel-corner.gif);
}
.z-popup .z-popup-tl,
.z-popup .z-popup-tr,
.z-popup .z-popup-bl,
.z-popup .z-popup-br {
	background-image:url(../images/zul/popup/pp-corner.gif);
}
.z-popup .z-popup-cl,
.z-popup .z-popup-cr {
	background-image: url(../images/zul/popup/pp-clr.gif);
}
.z-popup .z-popup-cm {
	background-image: url(../images/zul/popup/pp-cm.gif);
}
.z-slider-sphere-hor,
.z-slider-scale,
.z-slider-hor,
.z-slider-sphere-hor-center,
.z-slider-scale-center,
.z-slider-hor-center {
	background-image:url(../images/zul/slider/slider-bg.gif);
}
.z-slider-sphere-hor-btn,
.z-slider-scale-btn,
.z-slider-hor-btn {
	background-image : url(../images/zul/slider/slider-square.gif);
}
.z-slider-scale-btn {
	background-image : url(../images/zul/slider/slider-scale.gif);
}
.z-slider-sphere-ver,
.z-slider-ver,
.z-slider-sphere-ver-center,
.z-slider-ver-center {
	background-image:url(../images/zul/slider/slider-bg-ver.gif);
}
.z-slider-sphere-ver-btn,
.z-slider-ver-btn {
	background-image : url(../images/zul/slider/slider-v-square.gif);
}
.z-slider-sphere-hor-btn {
	background-image : url(../images/zul/slider/slider-circle.gif);
}
.z-slider-sphere-ver-btn {
	background-image : url(../images/zul/slider/slider-v-circle.gif);
}
.z-tab-hl,
.z-tab-hr {
	background-image: url(../images/zul/tab/tab-corner8.png);
}
.z-tab-hm {
	background-image: url(../images/zul/tab/tab-hm8.png);
}
.z-tab-ver-hl,
.z-tab-ver-hl .z-tab-ver-hr {
	background-image: url(../images/zul/tab/tab-v-corner.gif);
}
.z-tab-ver .z-tab-ver-hm {
	background-image: url(../images/zul/tab/tab-v-hm.png);
}
.z-tab-accordion-tl,
.z-tab-accordion-tr {
	background-image: url(../images/zul/tab/accd-corner.gif);
}
tr.z-tree-row td.z-tree-row-focus {
	background-image: url(../images/zul/common/focusd.png);
}
span.z-vfiletree-ico,span.z-vfiletree-firstspacer {
	background-image: url(../images/zul/tree/vfolder-toggle.gif);
}
span.z-vfiletree-tee, span.z-vfiletree-last {
	background-image: url(../images/zul/tree/ventity.gif);
}



<%-- zkex.css.dsp - 6 --%>
.z-border-layout-icon {
	background-image : url(../images/zkex/layout/borderlayout-btn.png);
}
.z-east-splt,
.z-west-splt {
	background-image:url(../images/zul/splt/splt-h.png);
	width:1px;
}
.z-north-splt,
.z-south-splt {
	background-image:url(../images/zul/splt/splt-v.png);
}
.z-west-header,
.z-center-header,
.z-east-header,
.z-north-header,
.z-south-header {
	background-image: url(../images/zkex/layout/borderlayout-hm.png);
}
.z-detail .z-detail-img {
	background-image: url(../images/zul/grid/row-expand.png);
}
tr.z-row .z-detail-outer {
	background-image: url(../images/zul/grid/detail-bg.png);
}
<%-- zkexie.css.dsp - 1 --%>
.z-detail .z-detail-img {
	background-image: url(../images/zul/grid/row-expand.gif);
}
<%-- box.css.dsp - 14 --%>
.z-splitter-hor-outer {
	background-image:url(../images/zul/splt/splt-h-ns.png);
}
.z-splitter-ver-outer .z-splitter-ver-outer-td {
	background-image:url(../images/zul/splt/splt-v-ns.png);
}
.z-splitter-hor {
	background-image:url(../images/zul/splt/splt-h.png);
	width: 1px;
}
.z-splitter-ver {
	background-image:url(../images/zul/splt/splt-v.png);
}
.z-splitter-hor-btn-l {
	background-image: url(../images/zul/splt/colps-l.png);
}
.z-splitter-hor-btn-r {
	background-image: url(../images/zul/splt/colps-r.png);
}
.z-splitter-ver-btn-t {
	background-image: url(../images/zul/splt/colps-t.png);
}
.z-splitter-ver-btn-b {
	background-image: url(../images/zul/splt/colps-b.png);
}
.z-splitter-os-hor-outer {
	background-image:url(../images/zul/splt/splt-h.gif);
}
.z-splitter-os-ver-outer .z-splitter-os-ver-outer-td {
	background-image:url(../images/zul/splt/splt-v.gif);
}
.z-splitter-os-hor-btn-l {
	background-image: url(../images/zul/splt/colps-l-os.gif);
}
.z-splitter-os-hor-btn-r {
	background-image: url(../images/zul/splt/colps-r-os.gif);
}
.z-splitter-os-ver-btn-t {
	background-image: url(../images/zul/splt/colps-t-os.gif);
}
.z-splitter-os-ver-btn-b {
	background-image: url(../images/zul/splt/colps-b-os.gif);
}
<%-- button.css.dsp - 4 --%>

.z-button{
	height:25px;
	padding: 0px;

	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius:10px; 
}

.z-button .z-button-tl, .z-button .z-button-tr, .z-button .z-button-bl, .z-button .z-button-br{
	background-image:url(../images/zul/button/btn-corner8.png);
}
.z-button .z-button-tm, .z-button .z-button-bm  {
	background-image:url(../images/zul/button/btn-x8.png);
}
.z-button .z-button-cl, .z-button .z-button-cr {
	background-image:url(../images/zul/button/btn-y8.png);
}
.z-button .z-button-cm {
	background-image:url(../images/zul/button/btn-ctr8.png);
}

.z-button-over .z-button-tl {
	background-position:-6px 0;
}
.z-button-over .z-button-tm {
	background-position:0 -6px;
}
.z-button-over .z-button-tr {
	background-position:-9px 0;
}
.z-button-over .z-button-cl {
	background-position:-6px 0px;
}
.z-button-over .z-button-cm {
  background-position:0 -500px;
}
.z-button-over .z-button-cr {
	background-position:-9px 0px;
}
.z-button-over .z-button-bl {
	background-position:-6px -3px;
}
.z-button-over .z-button-bm {
	background-position:0 -9px;
}
.z-button-over .z-button-br {
	background-position:-9px -3px;
}

.z-button-focus .z-button-tl {
	background-position:-6px 0;
}
.z-button-focus .z-button-tm {
	background-position:0 -6px;
}
.z-button-focus .z-button-tr {
	background-position:-9px 0;
}
.z-button-focus .z-button-cl {
	background-position:-6px 0px;
}
.z-button-focus .z-button-cm {
  background-position:0 -500px;
}
.z-button-focus .z-button-cr {
	background-position:-9px 0px;
}
.z-button-focus .z-button-bl {
	background-position:-6px -3px;
}
.z-button-focus .z-button-bm {
	background-position:0 -9px;
}
.z-button-focus .z-button-br {
	background-position:-9px -3px;
}

.z-button-clk .z-button-tl {
	background-position:-12px 0px;
}
.z-button-clk .z-button-tm{
	background-position:0 -12px;
}
.z-button-clk .z-button-tr {
	background-position:-15px 0px;
}
.z-button-clk .z-button-bl {
	background-position:-12px -3px;
}
.z-button-clk .z-button-bm {
	background-position:0 -15px;
}
.z-button-clk .z-button-br {
	background-position:-15px -3px;
}
.z-button-clk .z-button-cl {
	background-position:-12px 0px;
}
.z-button-clk .z-button-cm {
	background-position:0 -1000px;
}
.z-button-clk .z-button-cr {
	background-position:-15px 0px;
}

.z-button-clk .z-button {
	color:#FFFFFF;
}

<%-- combo.css.dsp - 15 --%>

.z-combobox{
	height:20px;
}

.z-combobox-inp {
	background-image: url(../images/zul/misc/text-bg8.gif);
	height:20px;
}
.z-combobox-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}
.z-combobox .z-combobox-img {
	background-image : url(../images/zul/button/combobtn9.png);
}

.z-combobox-pp .z-combo-item{
	height:20px;
}

.z-combobox-pp .z-combo-item-seld{
	background-image : url(../images/zul/common/focusd.png);
}
.z-combobox-pp .z-combo-item-over-seld{
	background-image : url(../images/zul/common/focusd.png);
}
.z-combobox-pp .z-combo-item-over{
	background-image : url(../images/zul/common/focusd.png);
}


.z-bandbox-inp {
	background-image: url(../images/zul/misc/text-bg.gif);
}
.z-bandbox-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}
.z-bandbox .z-bandbox-img {
	background-image : url(../images/zul/button/bandbtn9.png);
}

.z-datebox{
	height:20px;
}
.z-datebox-inp {
	background-image: url(../images/zul/misc/text-bg8.gif);
	height:20px;
}
.z-datebox-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}
.z-datebox .z-datebox-img {
	background-image : url(../images/zul/button/datebtn9.png);
}
.z-timebox-inp {
	background-image: url(../images/zul/misc/text-bg.gif);
}
.z-timebox-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}
.z-timebox .z-timebox-img {
	background-image : url(../images/zul/button/timebtn8.png);
}
.z-spinner-inp {
	background-image: url(../images/zul/misc/text-bg.gif);
}
.z-spinner-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}
.z-spinner .z-spinner-img {
	background-image : url(../images/zul/button/timebtn8.png);
}
<%-- grid.css.dsp - 13 --%>
div.z-grid-header tr.z-columns, div.z-grid-header tr.z-auxhead {
	background-image: url(../images/zul/grid/column-bg.png);
}
div.z-grid-header .z-column-sort div.z-column-cnt {
	background-image: url(../images/zul/sort/v_hint.gif);
}
div.z-grid-header .z-column-sort-asc div.z-column-cnt {
	background-image:url(../images/zul/sort/v_asc.gif);
}
div.z-grid-header .z-column-sort-asc, div.z-grid-header .z-column-sort-dsc {
	background-image:url(../images/zul/grid/column-over.png);
}
div.z-grid-header .z-column-sort-dsc div.z-column-cnt {
	background-image:url(../images/zul/sort/v_dsc.gif);
}
tr.z-group {
	background-image:url(../images/zul/grid/group_bg.png);
}
.z-group-img {
	background-image: url(../images/zul/common/toggle.png);
}
.z-group-foot {
	background-image:url(../images/zul/grid/groupfoot_bg.gif);
}
.z-column-btn {
	background-image: url(../images/zul/grid/hd-btn.png);
}
.z-column-over {
	background-image: url(../images/zul/grid/column-over.png);
}
.z-columns-menu-grouping .z-menu-item-img {
	background-image: url(../images/zul/grid/menu-group.png);
}
.z-columns-menu-asc .z-menu-item-img {
	background-image: url(../images/zul/grid/menu-arrowup.png);
}
.z-columns-menu-dsc .z-menu-item-img {
	background-image: url(../images/zul/grid/menu-arrowdown.png);
}
<%-- groupbox.css.dsp - 8 --%>
.z-groupbox-tl {
	background-image:url(../images/zul/groupbox/groupbox-corner.png);
}
.z-groupbox-tr{
	background-image:url(../images/zul/groupbox/groupbox-corner.png);
}
.z-groupbox-hl {
	background-image:url(../images/zul/groupbox/groupbox-hl.png);
}
.z-groupbox-hr {
	background-image:url(../images/zul/groupbox/groupbox-hr.png);
}
.z-groupbox-hm {
	background-image:url(../images/zul/groupbox/groupbox-hm.png);
}
.z-groupbox-bl {
	background-image: url(../images/zweb/shdlf.gif);
}
.z-groupbox-br {
	background-image: url(../images/zweb/shdrg.gif);
}
.z-groupbox-bm {
	background-image: url(../images/zweb/shdmd.gif);
}
<%-- listbox.css.dsp - 8 --%>
div.z-listbox-header tr.z-list-head, div.z-listbox-header tr.z-auxhead {
	background-image: url(../images/zul/grid/column-bg.png);
}
div.z-listbox-header th.z-list-header-sort div.z-list-header-cnt {
	background-image: url(../images/zul/sort/v_hint.gif);
}
div.z-listbox-header th.z-list-header-sort-asc div.z-list-header-cnt {
	background-image: url(../images/zul/sort/v_asc.gif);
}
div.z-listbox-header th.z-list-header-sort-dsc div.z-list-header-cnt {
	background-image: url(../images/zul/sort/v_dsc.gif);
}

tr.z-list-item td.z-list-item-focus {
	background-image: url(../images/zul/common/focusd.png);
}

tr.z-list-item-focus, div.z-listcell-cnt{
	background-image: url(../images/zul/common/focusd.png);
}

tr.z-list-item-seld, div.z-listcell-cnt{
	background-image: url(../images/zul/common/focusd.png);
}
tr.z-list-item-over, div.z-listcell-cnt{
	background-image: url(../images/zul/common/focusd.png);
}

tr.z-list-item-over-seld, div.z-listcell-cnt{
	background-image: url(../images/zul/common/focusd.png);
}






tr.z-list-group{
	background-image: url(../images/zul/grid/group_bg.gif);
}
.z-list-group-img{
	background-image: url(../images/zul/common/toggle.png);
}
.z-list-group-foot{
	background-image: url(../images/zul/grid/groupfoot_bg.gif);
}
<%-- menu.css.dsp - 10 --%>
.z-menubar-hor,.z-menubar-ver {
	background-image: url(../images/zul/common/bar-bg.png);
	height:25px;
}
.z-menubar-hor .z-menu-body-over .z-menu-inner-m  div {
	background-image:url(../images/zul/menu/btn-menu-hor-over.gif);
}
.z-menubar-ver .z-menu-body-over .z-menu-inner-m  div {
	background-image:url(../images/zul/menu/btn-menu-ver-over.gif);
}
.z-menu-body .z-menu-inner-m div {
	background-image:url(../images/zul/menu/btn-arrow.gif);
}
.z-menu-body-over .z-menu-inner-l,
.z-menu-body-seld .z-menu-inner-l,
.z-menu-item-body-over .z-menu-item-inner-l {
	background-image : url(../images/zul/menu/menu-btn.png);
}
.z-menu-body-over .z-menu-inner-r,
.z-menu-body-seld .z-menu-inner-r,
.z-menu-item-body-over .z-menu-item-inner-r {
	background-image : url(../images/zul/menu/menu-btn.png);
}
.z-menu-body-over .z-menu-inner-m,
.z-menu-body-seld .z-menu-inner-m,
.z-menu-item-body-over .z-menu-item-inner-m {
	background-image : url(../images/zul/menu/menu-btn.png);
}
.z-menu-popup {
	background-image : url(../images/zul/menu/pp-bg.png);
}
.z-menu-popup-cnt .z-menu .z-menu-cnt-img {
	background-image:url(../images/zul/menu/arrow.gif);
}
.z-menu-popup-cnt .z-menu-item-cnt-ck .z-menu-item-img {
	background-image:url(../images/zul/menu/checked.png);
}
.z-menu-popup-cnt .z-menu-item-cnt-unck .z-menu-item-img {
	background-image:url(../images/zul/menu/unchecked.png);
}

.z-menu-popup-cnt .z-menu-over,
.z-menu-popup-cnt .z-menu-item-over {
	background-image:url(../images/zul/menu/item-over.png);
	border:none;
	
}


<%-- paging.css.dsp - 8 --%>
.z-paging-os .z-paging-os-cnt {
	background-image:url(../images/zul/grid/column-bg.png);
}
.z-paging-os .z-paging-os-seld {
	background-image:url(../images/zul/grid/paging-os-seld.gif);
}
.z-paging {
	background-image: url(../images/zul/common/bar-bg.png);
	height:25px;
}
.z-paging .z-paging-sep {
	background-image:url(../images/zul/paging/pg-split.gif);
}
.z-paging-btn .z-paging-next {
	background-image:url(../images/zul/paging/pg-btn.png);
}
.z-paging-btn .z-paging-prev {
	background-image:url(../images/zul/paging/pg-btn.png);
}
.z-paging-btn .z-paging-last {
	background-image:url(../images/zul/paging/pg-btn.png);
}
.z-paging-btn .z-paging-first {
	background-image:url(../images/zul/paging/pg-btn.png);
}
<%-- panel.css.dsp - 11 --%>
.z-panel-tl {
	background-image: url(../images/zul/wnd/panel-corner.png);
}
.z-panel-tr {
	background-image: url(../images/zul/wnd/panel-corner.png);
}

.z-panel-header {
	background-image: url(../images/zul/wnd/panel-hm.png);
	-moz-border-radius-topright: 5px;
	-moz-border-radius-topleft: 5px;
	-webkit-border-top-right-radius: 5px;
	-webkit-border-top-left-radius: 5px;
}

.z-panel-hl {
	background-image: url(../images/zul/wnd/panel-hl.png);
}
.z-panel-hr {
	background-image: url(../images/zul/wnd/panel-hr.png);
}
.z-panel-hm {
	background-image: url(../images/zul/wnd/panel-hm.png);
}
.z-panel-cl,
.z-panel-fl {
	background-image: url(../images/zul/wnd/panel-clr.png);
}
.z-panel-cr,
.z-panel-fr {
	background-image: url(../images/zul/wnd/panel-clr.png);
}
.z-panel-bl {
	background-image: url(../images/zul/wnd/panel-corner.png);
}
.z-panel-br {
	background-image: url(../images/zul/wnd/panel-corner.png);
}
.z-panel-icon {
	background-image : url(../images/zul/wnd/ol-btn.png);
}
<%-- popup.css.dsp - 7 --%>
.z-popup .z-popup-tl {
	background-image:url(../images/zul/popup/pp-corner.png);
}
.z-popup .z-popup-tr {
	background-image:url(../images/zul/popup/pp-corner.png);
}
.z-popup .z-popup-cm {
	background-image: url(../images/zul/popup/pp-cm.png);
}
.z-popup .z-popup-cl {
	background-image: url(../images/zul/popup/pp-clr.png);
}
.z-popup .z-popup-cr {
	background-image: url(../images/zul/popup/pp-clr.png);
}
.z-popup .z-popup-bl {
	background-image:url(../images/zul/popup/pp-corner.png);
}
.z-popup .z-popup-br {
	background-image:url(../images/zul/popup/pp-corner.png);
}
<%-- separator.css.dsp - 2 --%>
.z-separator-hor-bar {
	background-image: url(../images/zweb/dot.gif);
}
.z-separator-ver-bar {
	background-image: url(../images/zweb/dot.gif);
}
<%-- slider.css.dsp - 10 --%>
.z-slider-sphere-hor,
.z-slider-scale,
.z-slider-hor {
	background-image:url(../images/zul/slider/slider-bg.png);
}
.z-slider-sphere-hor-center,
.z-slider-scale-center,
.z-slider-hor-center {
	background-image:url(../images/zul/slider/slider-bg.png);
}
.z-slider-sphere-hor-btn,
.z-slider-scale-btn,
.z-slider-hor-btn {
	background-image : url(../images/zul/slider/slider-square.png);
}
.z-slider-scale-btn {
	background-image : url(../images/zul/slider/slider-scale.gif);
}
.z-slider-scale-tick {
	background-image:url(../images/zul/slider/ticks.gif);
}
.z-slider-sphere-ver,
.z-slider-ver {
	background-image:url(../images/zul/slider/slider-bg-ver.png);
}
.z-slider-sphere-ver-center,
.z-slider-ver-center {
	background-image:url(../images/zul/slider/slider-bg-ver.png);
}
.z-slider-sphere-ver-btn,
.z-slider-ver-btn {
	background-image : url(../images/zul/slider/slider-v-square.png);
}
.z-slider-sphere-hor-btn {
	background-image : url(../images/zul/slider/slider-circle.png);
}
.z-slider-sphere-ver-btn {
	background-image : url(../images/zul/slider/slider-v-circle.png);
}
<%-- tabbox.css.dsp - 28 --%>
.z-tabs-scroll .z-tabs-cnt {
	background-image: url(../images/zul/tab/tabs-bg.png);
}
.z-tab-close {
	background-image: url(../images/zul/tab/tab-close.png);
}
.z-tab-hl {
	background-image: url(../images/zul/tab/tab-corner8.png);
}
.z-tab-hr {
	background-image: url(../images/zul/tab/tab-corner8.png);
}
.z-tab-hm {
	background-image: url(../images/zul/tab/tab-hm8.png);
}
.z-tabs-scroll .z-tabs-right-scroll {
	background-image: url(../images/zul/tab/scroll-r.png);
}
.z-tabs-scroll .z-tabs-left-scroll {
	background-image: url(../images/zul/tab/scroll-l.png);
}
.z-tabs-ver-scroll .z-tabs-ver-header {
	background-image: url(../images/zul/tab/tabs-v-bg.png);
}
.z-tab-ver-close {
	background-image: url(../images/zul/tab/tab-close.png);
}
.z-tab-ver-hl {
	background-image: url(../images/zul/tab/tab-v-corner.png);
}
.z-tab-ver-hl .z-tab-ver-hr {
	background-image: url(../images/zul/tab/tab-v-corner.png);
}
.z-tab-ver .z-tab-ver-hm {
	background-image: url(../images/zul/tab/tab-v-hm.png);
}
.z-tabs-ver-up-scroll {
	background-image: url(../images/zul/tab/scroll-u.png);
}
.z-tabs-ver-down-scroll {
	background-image: url(../images/zul/tab/scroll-d.png);
}
.z-tab-accordion-tl {
	background-image: url(../images/zul/tab/accd-corner.png);
}
.z-tab-accordion-tr {
	background-image: url(../images/zul/tab/accd-corner.png);
}
.z-tab-accordion-hl {
	background-image: url(../images/zul/tab/accd-hl.png);
}
.z-tab-accordion-hr {
	background-image: url(../images/zul/tab/accd-hr.png);
}
.z-tab-accordion-hm {
	background-image: url(../images/zul/tab/accd-hm.png);
}
.z-tab-accordion-close {
	background-image: url(../images/zul/common/close-off.gif);
}
.z-tab-accordion-close-over,
.z-tab-accordion .z-tab-accordion-close:hover {
	background-image: url(../images/zul/tab/accd-close-on.gif);
}
.z-tab-accordion-disd .z-tab-accordion-close:hover,
.z-tab-accordion-disd-seld .z-tab-accordion-close:hover {
	background-image: url(../images/zul/common/close-off.gif);
}
.z-tab-accordion-lite-tl {
	background-image: url(../images/zul/tab/lite-all.png);
}
.z-tab-accordion-lite-tr {
	background-image: url(../images/zul/tab/lite-all.png);
}
.z-tab-accordion-lite-tm {
	background-image: url(../images/zul/tab/lite-all.png);
}
.z-tab-accordion-lite-close {
	background-image: url(../images/zul/common/close-off.gif);
}
.z-tab-accordion-lite-close-over,
.z-tab-accordion-lite .z-tab-accordion-lite-close:hover {
	background-image: url(../images/zul/tab/lite-close-on.gif);
}
.z-tab-accordion-lite-disd .z-tab-accordion-lite-close:hover,
.z-tab-accordion-lite-disd-seld .z-tab-accordion-lite-close:hover {
	background-image: url(../images/zul/common/close-off.gif);
}
<%-- toolbar.css.dsp - 1 --%>
.z-toolbar {
	background-image: url(../images/zul/common/bar-bg.png);

}

<%-- tree.css.dsp - 18 --%>
div.z-tree-header tr.z-tree-cols, div.z-tree-header tr.z-auxhead {
	background-image: url(../images/zul/grid/column-bg.png);
}
span.z-tree-root-open, span.z-tree-tee-open, span.z-tree-last-open,
span.z-tree-root-close, span.z-tree-tee-close, span.z-tree-last-close {
	background-image: url(../images/zul/common/toggle.png);
}

tr.z-tree-row td.z-tree-row-focus, td.z-tree-cell-text {
	background-image: url(../images/zul/common/focusd.png);
	color:#FFFFFF;
}

tr.z-tree-row-seld, td.z-tree-cell-text {
	background-image: url(../images/zul/common/focusd.png);
	color:#FFFFFF;
}

tr.z-tree-row-over, td.z-tree-cell-text {
	background-image: url(../images/zul/common/focusd.png);
	color:#FFFFFF;
}

tr.z-tree-row-over-seld, td.z-tree-cell-text {
	background-image: url(../images/zul/common/focusd.png);
	color:#FFFFFF;
}

div.z-dottree-header tr.z-tree-cols, div.z-tree-header tr.z-auxhead  {
	background-image: url(../images/zul/grid/column-bg.png);
}
span.z-dottree-root-open, span.z-dottree-root-close{
	background-image: url(../images/zul/tree/dot-toggle.png);
}
span.z-dottree-tee-open {
	background-image: url(../images/zul/tree/tee-open.gif);
}
span.z-dottree-tee-close {
	background-image: url(../images/zul/tree/tee-close.gif);
}
span.z-dottree-last-open {
	background-image: url(../images/zul/tree/tee-last-open.gif);
}
span.z-dottree-last-close {
	background-image: url(../images/zul/tree/tee-last-close.gif);
}
span.z-dottree-tee {
	background-image: url(../images/zul/tree/tee.gif);
}
span.z-dottree-vbar {
	background-image: url(../images/zul/tree/tee-vbar.gif);
}
span.z-dottree-last {
	background-image: url(../images/zul/tree/tee-last.gif);
}
div.z-filetree-header tr.z-tree-cols, div.z-tree-header tr.z-auxhead  {
	background-image: url(../images/zul/grid/column-bg.png);
}
span.z-filetree-ico,span.z-filetree-firstspacer {
	background-image: url(../images/zul/tree/folder-toggle.gif);
}
span.z-filetree-tee, span.z-filetree-last {
	background-image: url(../images/zul/tree/entity.gif);
}
div.z-vfiletree-header tr.z-tree-cols, div.z-tree-header tr.z-auxhead  {
	background-image: url(../images/zul/grid/column-bg.png);
}
span.z-vfiletree-ico,span.z-vfiletree-firstspacer {
	background-image: url(../images/zul/tree/vfolder-toggle.png);
}
span.z-vfiletree-tee, span.z-vfiletree-last {
	background-image: url(../images/zul/tree/ventity.png);
}

<%-- widget.css.dsp - 2 --%>
.z-textbox, .z-decimalbox, .z-intbox, .z-longbox, .z-doublebox {
	background-image: url(../images/zul/misc/text-bg8.gif);
	height:20px;
}
.z-textbox-text-invalid,
.z-decimalbox-text-invalid,
.z-intbox-text-invalid,
.z-longbox-text-invalid,
.z-doublebox-text-invalid {
	background-image: url(../images/zul/misc/text-bg-invalid.gif);
}




<%-- window.css.dsp - 22 --%>

.z-window-modal,
.z-window-highlighted,
.z-window-overlapped,
.z-window-popup
{
	-moz-border-radius: 5px;
	-webkit-box-shadow: 5px;
	border-radius: 5px;

	-moz-box-shadow: 0 0 5px #333;
	-webkit-box-shadow: 0 0 5px #333;
	box-shadow: 0 0 5px #333;
}


.z-window-embedded-tl,
.z-window-modal-tl,
.z-window-highlighted-tl,
.z-window-overlapped-tl,
.z-window-popup-tl
{
	background-image: url(../images/zul/wnd/wnd-ol-corner.png);
}


.z-window-embedded-tr,
.z-window-modal-tr,
.z-window-highlighted-tr,
.z-window-overlapped-tr,
.z-window-popup-tr {
	background-image: url(../images/zul/wnd/wnd-ol-corner.png);
}


.z-window-embedded-hl,
.z-window-modal-hl,
.z-window-highlighted-hl,
.z-window-overlapped-hl,
.z-window-popup-hl {
	background-image: url(../images/zul/wnd/wnd-ol-hl.png);
}


.z-window-embedded-hr,
.z-window-modal-hr,
.z-window-highlighted-hr,
.z-window-overlapped-hr,
 .z-window-popup-hr {
	background-image: url(../images/zul/wnd/wnd-ol-hr.png);
}
.z-window-embedded-hr, .z-window-embedded-hr-noborder {
	background-image: url(../images/zul/wnd/wnd-hr.png);
}
.z-window-popup-hr {
	background-image: url(../images/zul/wnd/wnd-pop-hr.png);
}
.z-window-embedded-hm,
.z-window-modal-hm,
.z-window-highlighted-hm,
.z-window-overlapped-hm,
.z-window-popup-hm {
	background-image: url(../images/zul/wnd/wnd-ol-hm.png);
}
.z-window-embedded-hm {
	background-image: url(../images/zul/wnd/wnd-hm.png);
}
.z-window-popup-hm {
	background-image: url(../images/zul/wnd/wnd-pop-hm.png);
}
.z-window-modal-cl,
.z-window-highlighted-cl,
.z-window-overlapped-cl {
	background-image: url(../images/zul/wnd/wnd-ol-clr.png);
}
.z-window-modal-cr,
.z-window-highlighted-cr,
.z-window-overlapped-cr {
	background-image: url(../images/zul/wnd/wnd-ol-clr.png);
}



.z-window-embedded-bl,
.z-window-modal-bl,
.z-window-highlighted-bl,
.z-window-overlapped-bl,
.z-window-popup-bl
{
	background-image: url(../images/zul/wnd/wnd-ol-corner.png);
}

.z-window-embedded-br,
.z-window-modal-br,
.z-window-highlighted-br,
.z-window-overlapped-br,
.z-window-popup-br
{
	background-image: url(../images/zul/wnd/wnd-ol-corner.png);
}


.z-window-modal-icon,
.z-window-highlighted-icon,
.z-window-overlapped-icon, 
.z-window-embedded-icon,
.z-window-popup-icon{
	background-image : url(../images/zul/wnd/ol-btn.png);
}

/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = [
		{name: 'document', items: ['Source']},
		{name: 'clipboard', items: ['Cut','Copy','Paste','PasteText','-','Undo','Redo']}, 
		{name: 'basicstyles', items: ['Bold','Italic','Underline','Strike','Subscript','Superscript','-','CopyFormatting','RemoveFormat']}, 
		{name: 'paragraph', items: ['NumberedList','BulletedList','-','Outdent','Indent','-','JustifyLeft','JustifyCenter','JustifyRight']}, 
		{name: 'links', items: ['Link','Unlink']}, 
		{name: 'insert', items: ['Image','Table']}, 
		{name: 'styles', items: ['Font','FontSize']}, 
		{name: 'colors', items: ['TextColor', 'BGColor']},
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';

	config.extraPlugins = 'panelbutton,colorbutton,uploadwidget';
	//fileupload 경로 지정
	config.filebrowserImageUploadUrl= '/common/editorfileUpload?type=Images';
};

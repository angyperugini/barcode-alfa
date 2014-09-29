cordova.define("com.equipnet.plugin.barcode.barcode", function(require, exports, module) { 

var cordova = require('cordova');

var Barcode = {
	read : function(successCallback, errorCallback){
	cordova.exec(successCallback, errorCallback,'Barcode', 'read', []);//ImeiPlugin ->stringa che identifica 											      //l'oggetto e il rispettivo metodo richiamato
} 											//[] e relativi eventuali parametri
};

var barcode = new Barcode();
module.exports = barcode;

});
//cordova.define("com.equipnet.barcode.barcode", function(require, exports, module) { 
//
//	var cordova = require('cordova');
//
//	var barcode = {
//			read : function(successCallback, errorCallback){
//				cordova.exec(successCallback, errorCallback,'Barcode', 'read', []);//ImeiPlugin ->stringa che identifica
//				//l'oggetto e il rispettivo metodo richiamato
//			}	//[] e relativi eventuali parametri
//			
////			dbopen : function(successCallback, errorCallback){
////				cordova.exec(successCallback, errorCallback,'Barcode', 'dbopen', []);//ImeiPlugin ->stringa che identifica 											      
////			} 											
////	};
//
//	module.exports = barcode;
//
//});



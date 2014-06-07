print = lessenv.print;
quit = lessenv.quit;
readFile = lessenv.readFile;
delete arguments;

var basePath = function(path) {
	if (path != null) {
		return path.replace(/^(.*[\/\\])[^\/\\]*$/, '$1');
	}
	return "";
}

less.Parser.importer = function(path, paths, fn) {
	if (!/^\//.test(path) && !/^\w+:/.test(path)) {
		path = paths[0] + path;
	}
	if (path != null) {
		new(less.Parser)({ optimization: 3, paths: [basePath(path)] }).parse(String(lessenv.loader.load(path, lessenv.charset)), function (e, root) {
			if (e instanceof Object)
				throw e;
			fn(e, root);
			if (e instanceof Object)
				throw e;
		});
	}
};

var compile = function(source, location, compress) {
	var result;
	new (less.Parser) ({ optimization: 3, paths: [basePath(location)] }).parse(source, function (e, root) {
		if (e instanceof Object)
			throw e;
		result = root.toCSS();
		if (compress)
			result = exports.compressor.cssmin(result);
		if (e instanceof Object)
			throw e;
	});
	return result;
};


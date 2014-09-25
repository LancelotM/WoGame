var Window = Window || {};

Window.Show = function(content, clasz, callback)
{
	!Window._Context && Window._Render.call(this, clasz);
	
	var content = document.getElementById(content);
	if (!content) return;
	
	Window._Content.innerHTML = content.innerHTML;
	
	Window._Context.style.display = 'block';
	
	Window._Callback = callback;
};

Window.Hide = function()
{
	Window._Context.style.display = 'none';
	
	if (Window._Callback) Window._Callback.call();
};

Window._Render = function(clasz)
{
	var win = document.createElement('div'),
		container = document.createElement('div'),
		content = document.createElement('div'),
		hide = document.createElement('a');
	
	win.className = 'window';
	if (clasz) container.className = 'window-container ' + clasz;
	else container.className = 'window-container';
	content.className = 'window-content';
	hide.className = 'window-close';
	
	hide.href = 'javascript:Window.Hide();';
	
	container.appendChild(hide);
	container.appendChild(content);
	win.appendChild(container);
	
	document.body.appendChild(win);
	
	Window._Context = win;
	Window._Content = content;
	
	Window._Render = null;
	delete Window._Render;
};
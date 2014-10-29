window.alert = function(message)
{
	if (!window.alert._context) window.alert._render();
	window.alert._content.innerHTML = message;
	window.alert._context.style.display = 'block';
};

window.alert.close = function()
{
	window.alert._context.style.display = 'none';
};

window.alert._render = function()
{
	var win = document.createElement('div'),
		container = document.createElement('div'),
		title = document.createElement('h1'),
		content = document.createElement('div'),
		close = document.createElement('a'),
		button = document.createElement('a');
	
	win.className = 'alert';
	container.className = 'alert-container';
	content.className = 'alert-content';
	close.className = 'close';
	button.className = 'submit';
	
	title.innerHTML = '温馨提示';

	button.innerHTML = '确认';
	button.addEventListener('click', function()
	{
		window.alert.close();
	});
	
	close.addEventListener('click', function()
	{
		window.alert.close();
	});
	
	window.alert._context = win;
	window.alert._content = content;
	
	container.appendChild(title);
	container.appendChild(close);
	container.appendChild(content);
	container.appendChild(button);
	win.appendChild(container);
	
	document.body.appendChild(win);
	
	window.alert._render = null;
	delete window.alert._render;
};
/*
* Plugin name: Redsand Jquery Form Plugin
* Author: phannhuthan
* Uri: http://redsand.vn
* Version: 2.3
* Need using: jquery.form if use ajax upload ( thank to http://malsup.com/jquery/form/ )
*/


; (function ($) {

    //options: view info at http://malsup.com/jquery/form/#options-object

    $.fn.rsAjaxUpload = function (options) {

        if(this.length == 0) return this;

        var form = $('<form class="rs-uploader"></form>');
        var button = this;

        form.attr({
            selector: this.selector,
            enctype: 'multipart/form-data',
            method: 'post',
            action: '/base/uploadimage'
        });

        if(button.parents('form').length > 0){
            form.insertAfter(button.parents('form'));
        }
        else{
            form.insertAfter(button);
        }

        form.ajaxForm(options);

        button.rsFileUpload({
            form: form, 
            change: function() {
                form.submit();
            }
        });

        return this;
    }

    /* ex:
     * $('#button').rsFileUpload();
     * $('#input').rsFileUpload({ 
     *      form: '#form-id', 
     *      name: 'file', 
     *      change: function(fileupload){ 
     *            $('#input').val(fileupload.val());
     *      }
     * });
     *
     */
    $.fn.rsFileUpload=function(opts){

        if(this.length == 0) return this;        

		$(this).each(function(index){
			
			var button = $(this);

			options = $.extend({ 
				form: button.parents('form'), 
				name: 'file'
			}, opts);			
			
	
			var input = options.form.find(':file[name='+options.name+']') ;
			if(input.size() == 0) input = $('<input type="file" name="' + options.name + '"/>');

			input.css({
				opacity: 0,
				position: 'fixed',
				zIndex: 9999,
				left: -999,
				cursor: 'pointer'
			});
			
			button.css({
				cursor: 'pointer'
			});
			
			$(options.form).append(input);		
			

			//copy events
			var events = button.data('events');
			for (var type in events){
				for (var handler in events[type]){
					var func = events[type][handler].handler;
					if (typeof func !='function') continue;
					func = func.toString();
					func = func.substring(func.indexOf('{') + 1);
					func = func.substring(0, func.length-1);
					var d = 0, i = 0;
					for(var char in func){
						if(func[char] == '{') d++;
						if(func[char] == '}') d--;
						if(i == func.indexOf('$(this)') && d == 0){
							func=func.replace('$(this)','$("'+button.selector+'")');
						}
						if(i == func.indexOf('this') && d == 0){
							func=func.replace('this','$("'+button.selector+'").get()[0]');
						}
						i++;
					}
					input.bind(type, { func: func }, function(event){ eval(event.data.func) });
					button.unbind(events[type][handler].origType);
				}
			}

			button.click(function(event){
				event.preventDefaul();
			});

			button.mousemove(function (e) {
				input.css({
					width: 'auto',
					top: e.pageY - 10 - $(document).scrollTop(),
					left: e.pageX - input.width() - $(document).scrollLeft() + 15
				});
			});

			input.mousemove(function (e) {
				if (e.pageY < button.offset().top || e.pageY > button.offset().top + button.outerHeight() || e.pageX < button.offset().left || e.pageX > button.offset().left + button.outerWidth()){
					input.css({
						left: -999
					});
				}
				else{
					input.css({
						top: e.pageY - 10 - $(document).scrollTop(),
						left: e.pageX - input.width() - $(document).scrollLeft() + 15
					});
				}
			});

			if(options.change){
				input.change(function(){
					options.change(input);
				});
			}
		});
        return this;
    }
})(jQuery);


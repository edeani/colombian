/*window.addEventListener('beforeunload', function (e) {
  // Cancel the event as stated by the standard.
  e.preventDefault();
  // Chrome requires returnValue to be set.
  e.returnValue = 'Holaaaa';
});*/

window.onbeforeunload = function (e) {
  var e = e || window.event;
  // For IE and Firefox
  if (e) {
    e.returnValue = 'Tu mensaje para el usuario';
  }
  // For Safari
  return 'Tu mensaje para el usuario';
};



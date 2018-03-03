'use strict';

var sortable = getElementById('sortable');

dragula([getElementById('left-defaults'), getElementById('right-defaults')]);
dragula([getElementById('left-copy'), getElementById('right-copy')], { copy: true });
dragula([getElementById('left-events'), getElementById('right-events')])
  .on('drag', function (el) {
    el.className = el.className.replace('ex-moved', '');
  })
  .on('drop', function (el) {
    el.className += ' ex-moved';
  })
  .on('over', function (el, container) {
    container.className += ' ex-over';
  })
  .on('out', function (el, container) {
    container.className = container.className.replace('ex-over', '');
  });
dragula([getElementById('left-rollbacks'), getElementById('right-rollbacks')], { revertOnSpill: true });
dragula([getElementById('left-lovehandles'), getElementById('right-lovehandles')], {
  moves: function (el, container, handle) {
    return handle.classList.contains('handle');
  }
});

dragula([getElementById('left-rm-spill'), getElementById('right-rm-spill')], { removeOnSpill: true });
dragula([getElementById('left-copy-1tomany'), getElementById('right-copy-1tomany')], {
  copy: function (el, source) {
    return source === getElementById('left-copy-1tomany');
  },
  accepts: function (el, target) {
    return target !== getElementById('left-copy-1tomany');
  }
});

dragula([sortable]);

function clickHandler (e) {
  var target = e.target;
  if (target === sortable) {
    return;
  }
  target.innerHTML += ' [click!]';

  setTimeout(function () {
    target.innerHTML = target.innerHTML.replace(/ \[click!\]/g, '');
  }, 500);
}

function getElementById (id) {
  return document.getElementById(id);
}

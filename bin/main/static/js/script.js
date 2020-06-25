$(document).ready(function () {

  $('#sidebarCollapse').on('click', function () {

    $('#sidebar').toggleClass('open');

  });

});

// VIDEO PLAYER FOR MEDIA DETAIL
var tag = document.createElement('script');

tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

var player, iframe;

function onYouTubeIframeAPIReady() {
  player = new YT.Player('player', {
    events: {
      'onReady': onPlayerReady,
      'onStateChange': onPlayerStateChange
    }
  });
}

function onPlayerReady(event) {
  iframe = $('#player');
  player = event.target;
}

var done = false;

function onPlayerStateChange(event) {
  if (event.data == YT.PlayerState.PLAYING && !done) {
    done = true;
  }
}

function stopVideo() {
  player.stopVideo();
}


function launchMedia(mediaId, timerSet, type, mediaUrl) {
  var url = "";
  var episodeMediaId = null;
  var movieMediaId = null
  var isAMedia = true;
  if (type === "MOVIE") {
    url = '/movie/'
    movieMediaId = mediaId;
  }

  if (type === "SERIE") {
    url = '/episode/'
    episodeMediaId = mediaId;
  }

  iframe.css({ display: "block" })
  var src = mediaUrl;
  player.loadVideoByUrl(src, timerSet);
  var requestFullScreen = iframe[0].requestFullScreen || iframe[0].mozRequestFullScreen || iframe[0].webkitRequestFullScreen;
  if (requestFullScreen) {
    requestFullScreen.bind(iframe[0])();
  }
  if (isAMedia) {
    document.addEventListener('fullscreenchange', (event) => {

      if (document.fullscreenElement) {
        console.log(`Element: ${document.fullscreenElement.id} entered full-screen mode.`);
      } else {
        console.log('Leaving full-screen mode.');
        player.stopVideo();
        var date = formatDate(new Date());
        const stringValues = '{"userId":' + 0 + ', "movieMediaId":' + movieMediaId + ',"episodeMediaId":' + episodeMediaId + ',"startDate":' + '"' + date + '"' + ',"endDate":' + '"' + date + '"' + ',"watchDuration":' + player.getCurrentTime() + '}';
        console.log(stringValues)
        var jsonParsedData = JSON.parse(stringValues);
        // $.ajax({
        //   type: 'GET',
        //   url: '/User/History/Manage/' + stringValues,
        //   cache: false,
        //   success: function (result) {
        //     location.reload(); // TODO : Get a better solution
        //     iframe.css({ display: "none" })
        //   }
        // });
      }
    });
  }
}
// END VIDEO PLAYER

// UPDATE USER INFOS
var isEmailUpdateSelected = true;

function changeUpdateSelection() {
  isEmailUpdateSelected = getElementById("updateEmail").checked;
}
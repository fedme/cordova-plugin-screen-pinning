# Cordova ScreenPinning plugin

Cordova plugin exposing Android (API 21+) screen pinning APIs.

## Installation

Run the following command inside your project's folder:

```sh
cordova plugin add https://github.com/raimaj/cordova-plugin-screen-pinning.git
```

## Usage

### enterPinnedMode()

- **successCallback:** [Function optional] - success callback



```js
window.plugins.ScreenPinning.enterPinnedMode(successCallback, errorCallback);
```

### exitPinnedMode()

- **successCallback:** [Function optional] - success callback

- **errorCallback:** [Function(error) optional] - error callback, error message string is passed

```js
window.plugins.ScreenPinning.exitPinnedMode(successCallback, errorCallback);
```

## Device administrators



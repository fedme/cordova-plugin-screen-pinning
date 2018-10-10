# Cordova screen pinning plugin

Cordova plugin exposing Android (API 21+) screen pinning APIs.

## Installation

Run the following command inside your project's folder:

```sh
cordova plugin add cordova-plugin-screen-pinning
```

## Usage

### enterPinnedMode()

- **successCallback:** [Function optional] - success callback
- **errorCallback:** [Function(error) optional] - error callback

```js
cordova.plugins.screenPinning.enterPinnedMode(successCallback, errorCallback);
```

### exitPinnedMode()

- **successCallback:** [Function optional] - success callback
- **errorCallback:** [Function(error) optional] - error callback, error message string is passed

```js
cordova.plugins.screenPinning.exitPinnedMode(successCallback, errorCallback);
```




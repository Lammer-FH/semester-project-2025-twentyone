# GameSessionApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createGameSession**](#creategamesession) | **POST** /game-sessions | Start a new game session|
|[**deleteGameSession**](#deletegamesession) | **DELETE** /game-sessions/{id} | End a game session and clear its state|
|[**getGameSession**](#getgamesession) | **GET** /game-sessions/{id} | Get game session by ID (View Game Status)|
|[**updateGameSession**](#updategamesession) | **PUT** /game-sessions/{id} | Modify game-session details (e.g., change activity state)|

# **createGameSession**
> GameSessionDto createGameSession(gameSessionCreationRequestDto)


### Example

```typescript
import {
    GameSessionApi,
    Configuration,
    GameSessionCreationRequestDto
} from './api';

const configuration = new Configuration();
const apiInstance = new GameSessionApi(configuration);

let gameSessionCreationRequestDto: GameSessionCreationRequestDto; //

const { status, data } = await apiInstance.createGameSession(
    gameSessionCreationRequestDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **gameSessionCreationRequestDto** | **GameSessionCreationRequestDto**|  | |


### Return type

**GameSessionDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**201** | Game session created |  -  |
|**404** | Player not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **deleteGameSession**
> deleteGameSession()


### Example

```typescript
import {
    GameSessionApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new GameSessionApi(configuration);

const { status, data } = await apiInstance.deleteGameSession();
```

### Parameters
This endpoint does not have any parameters.


### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**204** | Game session deleted |  -  |
|**404** | Game session not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getGameSession**
> GameSessionDto getGameSession()


### Example

```typescript
import {
    GameSessionApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new GameSessionApi(configuration);

const { status, data } = await apiInstance.getGameSession();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**GameSessionDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Game session found |  -  |
|**404** | Game session not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updateGameSession**
> GameSessionDto updateGameSession(gameSessionUpdateRequestDto)


### Example

```typescript
import {
    GameSessionApi,
    Configuration,
    GameSessionUpdateRequestDto
} from './api';

const configuration = new Configuration();
const apiInstance = new GameSessionApi(configuration);

let gameSessionUpdateRequestDto: GameSessionUpdateRequestDto; //

const { status, data } = await apiInstance.updateGameSession(
    gameSessionUpdateRequestDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **gameSessionUpdateRequestDto** | **GameSessionUpdateRequestDto**|  | |


### Return type

**GameSessionDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Game session updated |  -  |
|**404** | Game session not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


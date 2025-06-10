# GameResultsApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getGameResultForSession**](#getgameresultforsession) | **GET** /game-results/session/{sessionId} | Get the game result for a specific game session|
|[**getResultsForPlayer**](#getresultsforplayer) | **GET** /game-results/player/{playerId} | Get all game results for a specific player|

# **getGameResultForSession**
> GameResultDto getGameResultForSession()


### Example

```typescript
import {
    GameResultsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new GameResultsApi(configuration);

let sessionId: number; // (default to undefined)

const { status, data } = await apiInstance.getGameResultForSession(
    sessionId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **sessionId** | [**number**] |  | defaults to undefined|


### Return type

**GameResultDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Game result retrieved successfully. |  -  |
|**404** | Game session or game result not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getResultsForPlayer**
> GameResultOverviewDto getResultsForPlayer()


### Example

```typescript
import {
    GameResultsApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new GameResultsApi(configuration);

let playerId: number; // (default to undefined)

const { status, data } = await apiInstance.getResultsForPlayer(
    playerId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **playerId** | [**number**] |  | defaults to undefined|


### Return type

**GameResultOverviewDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Game results retrieved successfully. |  -  |
|**404** | Player not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


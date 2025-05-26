# GameSessionControllerApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getGameStatus**](#getgamestatus) | **GET** /game-sessions/{id} | Zeigt den aktuellen Spielstatus an|

# **getGameStatus**
> GameSessionDto getGameStatus()


### Example

```typescript
import {
    GameSessionControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new GameSessionControllerApi(configuration);

let id: number; //ID der Spielsession (default to undefined)

const { status, data } = await apiInstance.getGameStatus(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**number**] | ID der Spielsession | defaults to undefined|


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
|**200** | Spielstatus erfolgreich geladen |  -  |
|**404** | Spielsession nicht gefunden |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


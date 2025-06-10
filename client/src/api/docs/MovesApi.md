# MovesApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createMove**](#createmove) | **POST** /game-sessions/{sessionId}/moves | Create a new move|
|[**getMoves**](#getmoves) | **GET** /game-sessions/{sessionId}/moves | Get all moves for a session|

# **createMove**
> MoveDto createMove(moveDto)


### Example

```typescript
import {
    MovesApi,
    Configuration,
    MoveDto
} from './api';

const configuration = new Configuration();
const apiInstance = new MovesApi(configuration);

let sessionId: number; // (default to undefined)
let moveDto: MoveDto; //

const { status, data } = await apiInstance.createMove(
    sessionId,
    moveDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **moveDto** | **MoveDto**|  | |
| **sessionId** | [**number**] |  | defaults to undefined|


### Return type

**MoveDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**201** | Move created |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getMoves**
> Array<MoveDto> getMoves()


### Example

```typescript
import {
    MovesApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new MovesApi(configuration);

let sessionId: number; // (default to undefined)

const { status, data } = await apiInstance.getMoves(
    sessionId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **sessionId** | [**number**] |  | defaults to undefined|


### Return type

**Array<MoveDto>**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Moves retrieved |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


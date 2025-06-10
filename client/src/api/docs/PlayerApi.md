# PlayerApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createPlayer**](#createplayer) | **POST** /players | Create new player|
|[**deletePlayer**](#deleteplayer) | **DELETE** /players/{id} | Delete player by ID|
|[**getPlayer**](#getplayer) | **GET** /players/{id} | Get player by ID|
|[**login**](#login) | **POST** /players/login | Login player with username and password|
|[**updatePlayer**](#updateplayer) | **PUT** /players/{id} | Update player by ID|

# **createPlayer**
> PlayerDto createPlayer(playerRequestDto)


### Example

```typescript
import {
    PlayerApi,
    Configuration,
    PlayerRequestDto
} from './api';

const configuration = new Configuration();
const apiInstance = new PlayerApi(configuration);

let playerRequestDto: PlayerRequestDto; //

const { status, data } = await apiInstance.createPlayer(
    playerRequestDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **playerRequestDto** | **PlayerRequestDto**|  | |


### Return type

**PlayerDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**201** | Player created |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **deletePlayer**
> deletePlayer()


### Example

```typescript
import {
    PlayerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PlayerApi(configuration);

let id: number; // (default to undefined)

const { status, data } = await apiInstance.deletePlayer(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**number**] |  | defaults to undefined|


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
|**204** | Player deleted |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getPlayer**
> PlayerDto getPlayer()


### Example

```typescript
import {
    PlayerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PlayerApi(configuration);

const { status, data } = await apiInstance.getPlayer();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**PlayerDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Player found |  -  |
|**404** | Player not found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **login**
> PlayerDto login(loginRequestDto)


### Example

```typescript
import {
    PlayerApi,
    Configuration,
    LoginRequestDto
} from './api';

const configuration = new Configuration();
const apiInstance = new PlayerApi(configuration);

let loginRequestDto: LoginRequestDto; //

const { status, data } = await apiInstance.login(
    loginRequestDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **loginRequestDto** | **LoginRequestDto**|  | |


### Return type

**PlayerDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Login successful |  -  |
|**401** | Invalid credentials |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updatePlayer**
> PlayerDto updatePlayer(playerRequestDto)


### Example

```typescript
import {
    PlayerApi,
    Configuration,
    PlayerRequestDto
} from './api';

const configuration = new Configuration();
const apiInstance = new PlayerApi(configuration);

let playerRequestDto: PlayerRequestDto; //

const { status, data } = await apiInstance.updatePlayer(
    playerRequestDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **playerRequestDto** | **PlayerRequestDto**|  | |


### Return type

**PlayerDto**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | Player updated |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


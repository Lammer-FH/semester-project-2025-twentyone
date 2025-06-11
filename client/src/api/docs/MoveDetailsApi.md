# MoveDetailsApi

All URIs are relative to *http://localhost:8080/twentyone/api*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**updateMove**](#updatemove) | **PUT** /moves/{id} | Update a move|

# **updateMove**
> MoveDto updateMove(moveDto)


### Example

```typescript
import {
    MoveDetailsApi,
    Configuration,
    MoveDto
} from './api';

const configuration = new Configuration();
const apiInstance = new MoveDetailsApi(configuration);

let moveDto: MoveDto; //

const { status, data } = await apiInstance.updateMove(
    moveDto
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **moveDto** | **MoveDto**|  | |


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
|**200** | Move updated |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


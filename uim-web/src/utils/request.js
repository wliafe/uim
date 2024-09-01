import {getToken} from "@/utils/auth";
import {ElMessage} from "element-plus";

async function request(method, url, data) {
    const headers = {"Content-Type": "application/json"};
    const token = getToken();
    if (token) headers.token = token;
    const request = {
        method: method,
        headers: headers,
    };
    if (data) request.body = JSON.stringify(data);
    const response = await fetch(url, request).then((response) =>
        response.json()
    );
    if (response.code !== 200) {
        ElMessage.error(response.message);
        return;
    }
    return response;
}

export default request;

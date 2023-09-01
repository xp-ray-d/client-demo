package main

import (
	"fmt"
)

func main() {
	rsp, err := invokeApi(
		"C0000035C0001",
		"https://api.kuaijie-pay.com/forward/pay/txn/v2/alipay/pay/wap",
		API_PRIVEKEY_RETOU,
		API_PUBLICKEY_RETOU)
	fmt.Println("..........", rsp, err)
}

package main

import (
	"bytes"
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/x509"
	"encoding/base64"
	"encoding/json"
	"strings"

	"github.com/parnurzeal/gorequest"
	"github.com/google/uuid"

	// "encoding/hex"
	// "crypto/tls"
	"encoding/pem"
	"fmt"
	"time"
)

const (
	API_PRIVEKEY_RETOU      = `MIIEpAIBAAKCAQEArwylJkhQfCsEbHn7J2WxLqaB0KfiV0/IVSusEF2aeMTYiVEuCPoyY+QYBvVk8ydAkfhMDvOGqbIBa13Ig4tz8A8i3AeXm2nk5yueaP5hd1qoiSKXwL5A5d7M8gNq5eUj6auijafIpDBLL7E6VPEhZNkDK1Mt+Z7uaZiA1JOJxsiTEPPBVYpYJ9qhJmGHGyEBu3vFis3+oB2yQUOzM8Z2+chGdyQ+H/yzqOpHisLm3g12GiG9Oh0SyQEh8jd0ZU8hlmicdgSDG7OsYJCWhi1F+q+BPVKqKw+V9CwJHw4DgPI6TmIbdCEDLibQgm9agndtzNZexvg/aaP3d5Z9T+3MjwIDAQABAoIBAQCJgM1ZvQQ8BS58Z3U6BU+g7XqbhMTvAOTEwoFueZiVtUrC0tnug1qBzPJyxOB/gB3gi8JzHChztDgZNpoDes+fisQ/Qld85TnBvSm6H9hAAR7SWjIKXWhtKQ6kLLsTy2LicshFrb6V+rmUxQqlHqS0qyMqLTS4GHf+yqSoVxyq4CRc7t2rSBZGiv04vwbfgi+GEl1NeBB1nQruDCo/gr6F4ZQ8y8QNYHg0FEPwY5p6ghx1P155QqQX1BjGw+wov3hMcY5qJnMG3iDA7374RTvZbl+2hPBNRoEKK6Et1VzPnIp2ZbFq34h5cNchxmOU0teUPi3elHAmaTPbdqNSzKDBAoGBAO8OQugJOO8PyX7NBjT0YEjlQuXHCx8wtQAC7DikcY58feQ0W1QE14r5NKjQd4XS0md0xV54+4XT0MYW8qJtoZjFVSb1vJsCJ3ptOBQXPwB93EEytgVmICTtErqEhxOhsCvhAEJmzE7LMDQQDtc2GgfPbVLMqoy1wPJWTMuaVtqFAoGBALt0+D7P7fJZqXFCQwQGpKPzInW0ngE41wKg4ydG5x5P28cybyk8Lo9DPYRJ/atySL9c27H55SJeSwzBOk2WjQboVupxavgQ3ovxb1hYO93SOh8yVNeZqHAwXqvETlA9fvP1FFAUDKRZcuN8NzEr3tQcuvLFlI6Q6Utc1GvsfVkDAoGBAKm6g3CB08WQfOylnumMNKys8kF4gAIGMAyxBoz03Jdg9nVHYyyVP4AxjdzqHmKKm87Ojq+T0GllSY7LjjwbAKgaO2otAVtBPUxkLXO+SYIM2owD+E5/AwWUuT5qiAk9PHr7Bpceq6KqCzB70j2k8PhoouMt402CYlrtQRsyGsaNAoGAa/f0H6F9aI/yAzTqCoCVBYU9fNQaCZQmavIlw3KYHaJbcMEAu5xZKNTOsso3HC+H7Qhb1YecTQ5IZcFJhnTHJ4pcvU20EcsNk0btHdI8MQgrx9oieceb93o0nPRFMvwoifTHzVOu8F5AyPu7gkvFXSBrIPdiGDco1+pQwxSEIecCgYBg5sbiu8cVJT7s/fcPN7LSLHDcQ+KjnjoOW+0LVsDxHFHnXCiYXHYQ8QTt41nqSOYpsFC+PWw3utbu4G6NmLE2ROvT0v0Xf7GCKQZMx/4zjnj001ihoYhsH5HTx3H43H8NAoecD99gS3HvTdg/IKQf4RIqcUcR97QDIFgBR5LPUA==`
	API_PUBLICKEY_RETOU     = `MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr7kN3GycZjK0McKi350AZsn8Djm23S2Z+OOrDxaa9DhEStT1aBJjS1DRCi/O4f30mGOq/isi/FXR14CXvTepP4AAcyxvLUn9R4N18Pyzo/CDqXEVXOtNvYe5/tNUkpQ0ZDl+tMN91xH5unNmAgGeTcQnl0quzD7dPl/+8P8BT50ovid3Osnh6iVa5I9mqXhpF+PUUnvjAZQGwMBtjL0HQdnNWmNwVA8UtnwQCFPjt9JmLHm4+bvZqLCUsbagfD3jTZF9Ula4FXARwzEZ+mHHXllEGGy8JOohqZBREeAwUn5Us1sUtCrhIxkYdm48USW/tdZjWzljjd/bkUu5Jj5IDQIDAQAB`
)

type GoApiArg struct {
	Body struct {
		MsgPublic struct {
			Version    string `json:"version"`
			CusReqTime string `json:"cusReqTime"`
			CusTraceNo string `json:"cusTraceNo"`
			CusCode    string `json:"cusCode"`
		} `json:"msgPublic"`
		MsgPrivate struct {
			TxnAmt  string `json:"txnAmt"`
			Memo    string `json:"memo"`
			BsnInfo struct {
				Subject string `json:"subject"`
				Expire  string `json:"expire"`
			} `json:"bsnInfo"`
			TxnRemark   string `json:"txnRemark"`
			NotifyUrl   string `json:"notifyUrl"`
			// CompleteUrl string `json:"completeUrl"`
		} `json:"msgPrivate"`
	} `json:"body"`
	Sign string `json:"sign"`
}

type GoApiReq struct {
	Sign string `json:"sign"`
	Body string `json:"body"`
}

type GoApiRsp struct {
	Body string `json:"body"`
	Sign string `json:"sign"`
}

type GoApiRspBody struct {
	MsgPublic struct {
		TxnDate    string `json:"txnDate"`
		CusCode    string `json:"cusCode"`
		CusTraceNo string `json:"cusTraceNo"`
		SysTraceNo string `json:"sysTraceNo"`
		SysRspTime string `json:"sysRspTime"`
		RspCode    string `json:"rspCode"`
		RspMsg     string `json:"rspMsg"`
	} `json:"msgPublic"`
	MsgPrivate struct {
		PayUrl   string `json:"payUrl"`
		TxnState string `json:"txnState"`
	} `json:"msgPrivate"`
}

func invokeApi(cuscode string, url string, prikey, pubkey string) (*GoApiRsp, error) {
	rsp := GoApiRsp{}
	req := GoApiArg{}
	req1 := GoApiReq{}

	req.Body.MsgPublic.Version = "2.0"
	req.Body.MsgPublic.CusReqTime = time.Now().Format("20060102150405")
	req.Body.MsgPublic.CusTraceNo = uuid.New().String()
	req.Body.MsgPublic.CusCode = cuscode //
	//
	req.Body.MsgPrivate.TxnAmt = "1"
	req.Body.MsgPrivate.Memo = "订单的自定义数据"               // "\u8ba2\u5355\u7684\u81ea\u5b9a\u4e49\u6570\u636e"
	req.Body.MsgPrivate.BsnInfo.Subject = "支付标题"           // "\u652f\u4ed8\u6807\u9898"
	req.Body.MsgPrivate.BsnInfo.Expire = time.Now().Add(900).Format("20060102150405")
	req.Body.MsgPrivate.NotifyUrl = "https://api.bsn.com/notify"
	req.Body.MsgPrivate.TxnRemark = "交易附言"
	//
	sdata, err := json.Marshal(req.Body)
	if err != nil {
		return &rsp, err
	}

	fmt.Println("..................", string(sdata))
	// s2, err := disableEscapeHtml(req.Body)
	// fmt.Println("..................", string(s2))
	req.Sign, err = RsaSignWithSha256(sdata, prikey)
	if err != nil {
		return &rsp, err
	}

	//
	req.Sign = strings.ReplaceAll(req.Sign, "/", `\/`)
	fmt.Println("............", req.Sign)
	adstr, _ := json.Marshal(req)

	adstr2 := strings.ReplaceAll(string(adstr), "\\\\", "\\")
	fmt.Println("............", string(adstr2))

	req1.Body = string(sdata) // json字符串
	req1.Sign = req.Sign

	adstr3, _ := json.Marshal(req1)
	adstr4 := strings.ReplaceAll(string(adstr3), "\\\\", "\\")

	//
	request := gorequest.New().Timeout(20 * time.Second)
	resp, msg, errs := request.Post(url).Set("Content-Type", "application/json").SendString(adstr4).End()
	if len(errs) != 0 { //请求失败
		return &rsp, fmt.Errorf("请求失败", errs)
	}
	if resp != nil && resp.StatusCode != 200 {
		return &rsp, fmt.Errorf("第三方失败", resp)
	}
	fmt.Println("..........", string(msg))
	err = json.Unmarshal([]byte(msg), &rsp)
	if err != nil {
		return &rsp, err
	}
	
	ok, err := RsaVerySignWithSha256([]byte(rsp.Body), []byte(rsp.Sign), pubkey)
	if err != nil {
		return &rsp, err
	}
	if !ok {
		return &rsp, fmt.Errorf("verysign error")
	}

	return &rsp, err
}

func disableEscapeHtml(data interface{}) (string, error) {
	bf := bytes.NewBuffer([]byte{})
	jsonEncoder := json.NewEncoder(bf)
	jsonEncoder.SetEscapeHTML(false)
	if err := jsonEncoder.Encode(data); err != nil {
		return "", err
	}
	return bf.String(), nil
}

// 签名
func RsaSignWithSha256(data []byte, privateRaw string) (string, error) {
	privateRaw = strings.Trim(privateRaw, "\n")
	if !strings.HasPrefix(privateRaw, "-----BEGIN RSA PRIVATE KEY-----") {
		nprikey := ""
		for {
			if len(privateRaw) > 64 {
				ad := privateRaw[:64] + "\n"
				nprikey += ad
				privateRaw = privateRaw[64:]
			} else {
				nprikey += privateRaw
				break
			}
		}
		privateRaw = fmt.Sprintf("%s\n%s\n%s", "-----BEGIN RSA PRIVATE KEY-----", nprikey, "-----END RSA PRIVATE KEY-----")
	}
	//
	block, _ := pem.Decode([]byte(privateRaw))
	if block == nil {
		return "", fmt.Errorf("block error")
	}
	//
	privateKey, err := x509.ParsePKCS1PrivateKey(block.Bytes)
	if err != nil {
		return "", err
	}
	//
	h := sha256.New()
	h.Write(data)
	hashed := h.Sum(nil)

	signature, err := rsa.SignPKCS1v15(rand.Reader, privateKey, crypto.SHA256, hashed)
	if err != nil {
		return "", err
	}
	encodedSig := base64.StdEncoding.EncodeToString(signature)
	fmt.Println("............", encodedSig)
	return encodedSig, nil
}

// 验证,keyBytes公钥
func RsaVerySignWithSha256(data, signData []byte, pubkey string) (bool, error) {
	if !strings.HasPrefix(pubkey, "-----BEGIN PUBLIC KEY-----") {
		pubkey = "-----BEGIN PUBLIC KEY-----\n" + pubkey + "\n-----END PUBLIC KEY-----"
	}
	sign, err := base64.StdEncoding.DecodeString(string(signData))
	if err != nil {
		return false, err
	}
	block, _ := pem.Decode([]byte(pubkey))
	if block == nil {
		return false, fmt.Errorf("block error")
	}
	pubKey, err := x509.ParsePKIXPublicKey(block.Bytes)
	if err != nil {
		return false, err
	}

	hashed := sha256.Sum256(data)
	err = rsa.VerifyPKCS1v15(pubKey.(*rsa.PublicKey), crypto.SHA256, hashed[:], sign)
	if err != nil {
		return false, err
	}
	return true, nil
}

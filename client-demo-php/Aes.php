<?php
/**
 *
 * +------------------------------------------------------------+
 * @category Aes.php
 * +------------------------------------------------------------+
 * AES加密算法
 * 密码学中的高级加密标准（Advanced Encryption Standard，AES），又称Rijndael加密法，是美国联邦政府采用的一种* 区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
 * Aes加解密工具类
 * AES是当前最为常用的安全对称加密算法
 * AES-128：16位密钥key
 * AES-192：24位密钥key
 * AES-256：32位密钥key
 * OpenSSL 分别都为我们提供了公钥的加解密和私钥的加解密函数
 * +------------------------------------------------------------+
 *
 * @author sensus
 * Created on 2023-03-17 11:00
 */


class Aes
{
    const TYPE_MCRYPT = 1;
    const TYPE_OPENSSL = 2;
    private $type; // 加密方式：1、mcrypt；2、openssl 默认2
    private $cipher = 'AES-128-ECB'; // 加密算法：openssl_get_cipher_methods() 获取可用的 cipher 方法
    private $mode = 'ecb'; // 算法模式：cbc  cfb  ctr  ecb  ncfb  nofb  ofb  stream
    private $key = '2ajs7c9dnfyf5vjkd9ejdklfj9k22931'; // 6670f0c7d8dd3c1d21e8e3e0c7739c64
    private $iv = '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'; // 默认初始化向量

    /*
     * 构造函数
     * @param key 密钥
     * @param type 加密类型：1、mcrypt；2、openssl
     */
    public function __construct($key = '', $type = self::TYPE_OPENSSL, $cipher = 'AES-128-ECB')
    {
        $this->key    = !empty($key) ? $key : $this->key;
        $this->type   = $type;
        $this->cipher = $cipher;
    }

    /**
     * Aes加密
     * @param string $plainData 待加密原文
     * @return string
     */
    public function encrypt($plainData)
    {
        $type   = $this->type;
        $cipher = $this->cipher;
        $mode   = $this->mode;
        $key    = $this->key;
        $iv     = $this->iv;
        if ($type == self::TYPE_MCRYPT) {
            $size      = mcrypt_get_block_size($cipher, $mode);
            $encrypt   = self::pkcs5Pad($plainData, $size);
            $passCrypt = mcrypt_encrypt($cipher, $key, $encrypt, $mode, $iv);
            $ret       = base64_encode($passCrypt);
        } else {
            $data = openssl_encrypt($plainData, $cipher, $key, OPENSSL_RAW_DATA);
            $ret  = base64_encode($data);
        }
        return $ret;
    }

    /**
     * 填充算法
     * @param string $source
     * @return string
     */
    function addPKCS7Padding($source)
    {
        $source = trim($source);
        $block = 16;

        $pad = $block - (strlen($source) % $block);
        if ($pad <= $block) {
            $char = chr($pad);
            $source .= str_repeat($char, $pad);
        }
        return $source;
    }

    /**
     * 填充算法
     * @param $string
     * @param int $blocksize
     * @return string
     */
    public function addPkcs5Padding($string, $blocksize = 16) {
        //取得字符串长度
        $len = strlen($string);
        //取得补码的长度
        $pad = $blocksize - ($len % $blocksize);
        //用ASCII码为补码长度的字符， 补足最后一段
        $string .= str_repeat(chr($pad), $pad);
        return $string;
    }

    /**
     * Aes解密
     * @param string $encData 待解密密文
     * @return bool|string
     */
    public function decrypt($encData)
    {
        $type   = $this->type;
        $cipher = $this->cipher;
        $mode   = $this->mode;
        $key    = $this->key;
        $iv     = $this->iv;
        if ($type == self::TYPE_MCRYPT) {
            $str    = base64_decode($encData);
            $orData = mcrypt_decrypt($cipher, $key, $str, $mode, $iv);
            $str    = self::pkcs5Unpad($orData);
            return $str;
        } else {
            $encryptedData = base64_decode($encData);
            $ret           = openssl_decrypt($encryptedData, $cipher, $key, OPENSSL_RAW_DATA);
        }
        return $ret;
    }

    /**
     * pcks5解密
     * @param $text
     * @return bool|string
     */
    public function pkcs5Unpad($text)
    {
        $pad = ord($text{strlen($text) - 1});
        if ($pad > strlen($text)) {
            return false;
        }
        if (strspn($text, chr($pad), strlen($text) - $pad) != $pad) {
            return false;
        }
        return substr($text, 0, -1 * $pad);
    }

    /**
     * pkcs5加密
     * @param $text
     * @param $blockSize
     * @return string
     */
    public function pkcs5Pad($text, $blockSize)
    {
        $pad = $blockSize - (strlen($text) % $blockSize);
        return $text . str_repeat(chr($pad), $pad);
    }
}
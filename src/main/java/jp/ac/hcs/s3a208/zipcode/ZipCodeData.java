package jp.ac.hcs.s3a208.zipcode;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 1件分の郵便番号
*/


@Data
@NoArgsConstructor
public class ZipCodeData {

   /** 郵便番号 */
   private String zipcode;

   /** 都道府県コード */
   private String prefcode;

   /** 都道府県名 */
   private String address1;

   /** 市町村名 */
   private String address2;

   /** 町域名 */
   private String address3;

   /** 都道府県名カナ */
   private String kana1;

   /** 市町村名カナ */
   private String kana2;

   /** 町域名名カナ */
   private String kana3;
}

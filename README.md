# Demo Session Management untuk Replikasi #

Menyimpan data session di database supaya aplikasi bisa direplikasi

## Cara menjalankan ##

1. Jalankan aplikasi di port 10001


    ```
    cd belajar-spring-session-app
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=10001
    ```

2. Jalankan aplikasi sekali lagi, di port 10002. Buka command prompt baru

    ```
    cd belajar-spring-session-app
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=10002
    ```

3. Jalankan load balancer

    ```
    cd belajar-spring-session-lb
    mvn spring-boot:run
    ```

4. Akses ke aplikasi melalui load-balancer di [http://localhost:10000/aplikasi/](http://localhost:10000)

    > Refresh browser berkali-kali dan perhatikan alamat IP dan port. Apabila load-balancer sudah diarahkan ke dua instance, nilainya akan berubah-ubah.

5. Isi data ke dalam session di [http://localhost:10000/aplikasi/sessiondata/list](http://localhost:10000/aplikasi/sessiondata/list)

    > Apabila data session belum disimpan di database, tidak akan bisa login karena request di-round robin.
    > Coba disable salah satu backend **di load balancer**. Harusnya bisa login.
    > Setelah data session disimpan di database, aktifkan lagi kedua backend. Harusnya bisa login dan simpan data seperti biasa.

## Cara Enable Spring Sessions JDBC ##

1. Tambahkan dependensi di [pom.xml](belajar-spring-session-app/pom.xml)

    ```xml
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-jdbc</artifactId>
    </dependency>
    ```

2. Tambahkan konfigurasi di properties untuk generate skema database untuk menyimpan data session

    ```
    spring.session.jdbc.initialize-schema=always
    spring.session.jdbc.schema=classpath:org/springframework/session/jdbc/schema-postgresql.sql
    ```

    > Sesuaikan file schema dengan merek database yang Anda pakai. Daftar lengkap database yang sudah disediakan skemanya bisa dilihat [di sini](https://github.com/spring-projects/spring-session/tree/master/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc)

3. Keseluruhan perbedaan antara aplikasi Spring Boot yang simpan session di memori dan di database bisa dilihat [di sini](https://github.com/endymuhardin/belajar-spring-session/commit/2c56569da1cb3df41264d184de55f256bffae04a).

    > Apa-apaan ini? Kok sedikit sekali perubahannya? Masa cuma menambah 6 baris konfigurasi sudah bisa pindah semua ke database ???

    Ya itulah kenapa disebut `Spring Boot`, karena sudah disediakan semua oleh pembuatnya, sehingga kita pemakai tinggal maga**boot**

4. Untuk disable Spring Sessions JDBC (kembali simpan data session di memori), cukup lakukan hal berikut:

    * Hapus/comment dependensi `spring-session-jdbc` di `pom.xml`
    * **Optional** : Hapus tabel `spring_session` dan `spring_session_attributes` di database
    * **Optional** : Hapus/comment dua baris konfigurasi `spring.session.jdbc` di `application.properties

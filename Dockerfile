FROM stilliard/pure-ftpd:hardened

RUN apt-get update && apt-get install -y expect

RUN mkdir -p /home/ftpusers/sample && chown ftpuser:ftpgroup /home/ftpusers/sample
COPY add_user.exp /tmp/
RUN expect /tmp/add_user.exp

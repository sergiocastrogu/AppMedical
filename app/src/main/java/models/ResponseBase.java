package models;

public class ResponseBase <T>{
        private int idCodigo;
        private String message;
        private T data;

        public int getIdCodigo() {
                return idCodigo;
        }

        public void setIdCodigo(int idCodigo) {
                this.idCodigo = idCodigo;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public T getData() {
                return data;
        }

        public void setData(T data) {
                this.data = data;
        }
}

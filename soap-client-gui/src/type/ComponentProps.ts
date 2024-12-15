import {ProductDataView} from "./BffInterface.ts";

export interface FileUploadProps {
    onUpload: (file: File) => void;
}


export interface ResultTableProps {
    data: Array<ProductDataView>;
}
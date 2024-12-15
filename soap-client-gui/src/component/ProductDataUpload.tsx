import {FileUploadProps} from "../type/ComponentProps.ts";
import {useState} from "react";
import { UploadOutlined } from '@ant-design/icons';
import {Button, message, Upload} from "antd";

export function ProductDataUpload({ onUpload }: FileUploadProps) {
    const [file, setFile] = useState<File | null>(null);

    const handleUpload = () => {
        if (!file) {
            message.error('Please select a file to upload!');
            return;
        }
        onUpload(file);
    };

    return (
        <div>
            <Upload
                beforeUpload={(file) => {
                    setFile(file);
                    return false; // Prevent auto-upload
                }}
                onRemove={() => setFile(null)}
                maxCount={1}
            >
                <Button icon={<UploadOutlined />}>Select File</Button>
            </Upload>
            <Button
                type="primary"
                onClick={handleUpload}
                style={{ marginTop: '10px' }}
                disabled={!file}
            >
                Upload
            </Button>
        </div>
    );
}

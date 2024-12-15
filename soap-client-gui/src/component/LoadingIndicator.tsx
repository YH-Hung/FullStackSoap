import {Spin} from "antd";

export function LoadingIndicator() {
    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Spin tip="Processing... Please wait." size="large" />
        </div>
    );
}